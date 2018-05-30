package org.techtown.capstoneproject.tab.second.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.capstoneproject.MainActivity;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.service.api.ApiService_Chemical;
import org.techtown.capstoneproject.service.api.MyRetrofit2;
import org.techtown.capstoneproject.service.api.UploadService;
import org.techtown.capstoneproject.service.dto.TestDTO;
import org.techtown.capstoneproject.tab.second.search.result.modification.Modification;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

/*
 * Created by ShimPiggy on 2018-05-07.
 * Modified by ShimPiggy on 2018-05-09. - Camera
 * Modified by ShimPiggy on 2018-05-19. - modify changed design and control
 * Modified by ShimPiggy on 2018-05-23. - image
 */

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int CROP_FROM_CAMERA = 2;
    private static final int PICK_FROM_FILE = 1818;

    private Uri mImageCaptureUri;
    private String fileName;

    private ImageButton btnName;
    private ImageButton btnDetail;
    private ImageButton btnBarcode;
    private ImageButton btnWrite;

    private Retrofit retrofit;
    private ApiService_Chemical apiService_chemical;
    static ArrayList<TestDTO> arrayList;

    public FragmentSearch() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        verifyStoragePermissions(getActivity());

        Init(view);

        getChemicalNameList();

        return view;
    }

    //persmission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }


    public void Init(View view) {
        btnName = (ImageButton) view.findViewById(R.id.btn_name);//제품명
        btnDetail = (ImageButton) view.findViewById(R.id.btn_detail);//화학성분
        btnBarcode = (ImageButton) view.findViewById(R.id.btn_barcode);//바코드
        btnWrite = (ImageButton) view.findViewById(R.id.btn_write);//직접 쓰기

        btnBarcode.setOnClickListener(this);
        btnName.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
        btnWrite.setOnClickListener(this);

        arrayList = new ArrayList<>();
    }//init

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_barcode:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent chooser = Intent.createChooser(intent, "이미지를 불러옵니다");

                startActivityForResult(chooser, PICK_FROM_FILE);
                break;
            case R.id.btn_detail:
                ButtonDetailListener(v);
                break;
            case R.id.btn_name:
                ButtonNameListener(v);
                break;
            case R.id.btn_write:
                Intent intent1 = new Intent(getActivity().getApplicationContext(), WriteChemical.class);
                intent1.putExtra("type", "tab");
                startActivity(intent1);
                break;
        }
    }

    //자동완성을 위한 성분리스트 전체 항목을 불러온다.
    private void getChemicalNameList() {
        if (WriteChemical.item == null) {

            retrofit = new Retrofit.Builder().baseUrl(ApiService_Chemical.API_URL).build();
            apiService_chemical = retrofit.create(ApiService_Chemical.class);
            Call<ResponseBody> getList = apiService_chemical.getNameList("!");
            getList.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String tempList = response.body().string();
                        JSONObject jsonObject = new JSONObject(tempList);
                        WriteChemical.item = new String[jsonObject.length()];

                        for (int i = 0; i < jsonObject.length(); i++) {
                            WriteChemical.item[i] = jsonObject.getString(String.valueOf(i));
                        }

                    } catch (IOException e) {
                        Log.i("retrofiError", e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }


    public void ButtonNameListener(View v) {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(getActivity())
                .setTitle("사진 촬영을 하겠습니다.")
                .setPositiveButton("확인", cameraListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }//ButtonNameListener

    public void ButtonDetailListener(View v) {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(getActivity())
                .setTitle("사진 촬영을 하겠습니다.")
                .setPositiveButton("확인", cameraListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }//ButtonDetailListener


    /**
     * 카메라에서 이미지 가져오기
     */
    private void doTakePhotoAction() {
        //촬영 후 이미지 가져오기
    /*
     * 참고 해볼곳
     * http://2009.hfoss.org/Tutorial:Camera_and_Gallery_Demo
     * http://stackoverflow.com/questions/1050297/how-to-get-the-url-of-the-captured-image
     * http://www.damonkohler.com/2009/02/android-recipes.html
     * http://www.firstclown.us/tag/android/
     */
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        mImageCaptureUri = getOutputMediaFileUri();

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

        startActivityForResult(intent, PICK_FROM_CAMERA);
    }//doTakePhotoAction

    /**
     * 앨범에서 이미지 가져오기
     */
    /*
    private void doTakeAlbumAction()
    {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }//doTakeAlbumAction
    */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {

            case PICK_FROM_FILE: {
                Uri selectImage = data.getData();
                uploadImage(selectImage);
                break;
            }
            case CROP_FROM_CAMERA: {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                final Bundle extras = data.getExtras();//전체 사진

                if (extras != null) {
                    Bitmap cropPhoto = extras.getParcelable("data");//crop된 bitmap

                    saveBitmaptoJpeg(cropPhoto, getString(R.string.app_name), fileName + "_crop");

                    File cropPhotoFile = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + getString(R.string.app_name) + File.separator + fileName + "_crop.jpg");
                    Log.e(">>>>>>>tempor", cropPhotoFile.getPath());

                }

                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                Log.e(">>>>>>>tempor", f.getPath());
               /* if (f.exists()) {
                    f.delete();
                }*/
                nextActivity();

                break;
            }

            case PICK_FROM_CAMERA: {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                //crop한 이미지를 저장할 때
                intent.putExtra("outputX", 200);//crop한 이미지의 x축
                intent.putExtra("outputY", 200);//crop한 이미지의 y축
  /*              intent.putExtra("aspectX", 1);//crop박스의 x축 비율
                intent.putExtra("aspectY", 1);//crop박스의 y축 비율*/
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }//
        }//switch
    }//onActivityResult

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(getRealPathFromURI(fileUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }


    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Create a file Uri for saving an image
     */
    private Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), getString(R.string.app_name));
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(getString(R.string.app_name), "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName + ".jpg");

        return mediaFile;
    }//getOutputMediaFile


    public void nextActivity() {
        Intent intent = new Intent(getActivity().getApplicationContext(), Modification.class);

        inputData();

        intent.putExtra("result", arrayList);
        startActivity(intent);
    }

    public void inputData() {
        //임시 데이터
        TestDTO[] items = new TestDTO[5];

        String name = "에칠헥실메톡시신나메이트";

        for (int i = 0; i < items.length; i++) {
            items[i] = new TestDTO(i + 1, name, true, true, true);
            arrayList.add(items[i]);
        }

        arrayList.get(1).setBool(false, true, true);
        arrayList.get(2).setBool(true, false, true);
        arrayList.get(3).setBool(true, true, false);
        arrayList.get(4).setBool(false, true, false);
    }//inputData

    public void uploadImage(Uri uri) {
        UploadService service = MyRetrofit2.getRetrofit2().create(UploadService.class);

        File file = new File(getRealPathFromURI(uri));
        MultipartBody.Part body1 = prepareFilePart("image", uri);

        RequestBody description = createPartFromString("file");

        Call<ResponseBody> call = service.uploadFile(description, body1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }//uploadImage

    /**
     * Image SDCard Save (input Bitmap -> saved file JPEG)
     * Writer intruder(Kwangseob Kim)
     *
     * @param bitmap : input bitmap file
     * @param folder : input folder name
     * @param name   : output file name
     */
    public static void saveBitmaptoJpeg(Bitmap bitmap, String folder, String name) {
        String ex_storage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        // Get Absolute Path in External Sdcard
        String foler_name = "/" + folder + "/";
        String file_name = name + ".jpg";
        String string_path = ex_storage + foler_name;

        File file_path;
        try {
            file_path = new File(string_path);
            if (!file_path.isDirectory()) {
                file_path.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(string_path + file_name);

            int height = bitmap.getHeight();
            int width = bitmap.getWidth();

            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();

        } catch (FileNotFoundException exception) {
            Log.e("FileNotFoundException", exception.getMessage());
        } catch (IOException exception) {
            Log.e("IOException", exception.getMessage());
        }
    }

}//FragmentSearch