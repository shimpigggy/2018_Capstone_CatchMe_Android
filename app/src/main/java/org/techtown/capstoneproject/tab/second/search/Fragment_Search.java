package org.techtown.capstoneproject.tab.second.search;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.tab.second.search.result.modification.ResultModification;

import static android.app.Activity.RESULT_OK;

/*
 * Created by ShimPiggy on 2018-05-07.
 * Modified by ShimPiggy on 2018-05-09. - Camera
 * Modified by ShimPiggy on 2018-05-19. - modify changed design and control
 * Modified by ShimPiggy on 2018-05-23. - image
 */

public class Fragment_Search extends Fragment {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;

    private Uri mImageCaptureUri;
    private Uri fileUri;

    private ImageButton btn_name;
    private ImageButton btn_detail;
    private ImageButton btn_barcode;
    private ImageButton btn_wirte;

    public Fragment_Search() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Init(view);
        buttonSetting();

        return view;
    }

    public void Init(View view) {
        btn_name = (ImageButton) view.findViewById(R.id.btn_name);//제품명
        btn_detail = (ImageButton) view.findViewById(R.id.btn_detail);//화학성분
        btn_barcode = (ImageButton) view.findViewById(R.id.btn_barcode);
        btn_wirte = (ImageButton) view.findViewById(R.id.btn_write);//직접 쓰기

    }//init

    public void buttonSetting() {
        btn_name.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ButtonNameListener(v);
            }
        });

        btn_detail.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ButtonDetailListener(v);
            }
        });

        btn_barcode.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ButtonBarcodeListener(v);
            }
        });

        btn_wirte.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                ButtonWriteListener(v);
            }
        });
    }//buttonSetting

    public void ButtonNameListener(View v) {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };

        /*
        //갤러리에서 사진 가져오기
        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };*/

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(getActivity())
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진촬영", cameraListener)
                //       .setNeutralButton("앨범선택", albumListener)
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

        /*
        //갤러리에서 사진 가져오기
        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };*/

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(getActivity())
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진촬영", cameraListener)
                //       .setNeutralButton("앨범선택", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }//ButtonDetailListener

    public void ButtonBarcodeListener(View v) {
        Toast.makeText(v.getContext(), "Barcode!", Toast.LENGTH_SHORT).show();
    }//ButtonBarcodeListener

    public void ButtonWriteListener(View v) {
      //  Toast.makeText(v.getContext(), "Write_chemical!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity().getApplicationContext(), WriteChemical.class);
        intent.putExtra("type","tab");
        startActivity(intent);
    }//ButtonWriteListener


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

        // 임시로 사용할 파일의 경로를 생성
        /*String photoName = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        String appName = getString(R.string.app_name);

        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),appName);

        File imageFile = new File(directory.getPath()+File.separator+photoName);

        mImageCaptureUri = Uri.fromFile(imageFile);*/

        // fileUri = getOutputMediaFileUri();
        mImageCaptureUri = getOutputMediaFileUri();

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        //intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,fileUri);

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
            case CROP_FROM_CAMERA: {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                final Bundle extras = data.getExtras();//전체 사진

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");//crop된 bitmap



                    Intent intent = new Intent(getActivity().getApplicationContext(), ResultModification.class);
                    startActivity(intent);
                }

                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                Log.e(">>>>>>>tempor", f.getPath());
               /* if (f.exists()) {
                    f.delete();
                }*/

                break;
            }

           /* case PICK_FROM_ALBUM: {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

                mImageCaptureUri = data.getData();
            }*/

            case PICK_FROM_CAMERA: {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                //crop한 이미지를 저장할 때
                intent.putExtra("outputX", 300);//crop한 이미지의 x축
                intent.putExtra("outputY", 300);//crop한 이미지의 y축
                intent.putExtra("aspectX", 1);//crop박스의 x축 비율
                intent.putExtra("aspectY", 1);//crop박스의 y축 비율
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }//
        }//switch
    }//onActivityResult

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
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + timeStamp+".jpg");

        return mediaFile;
    }//getOutputMediaFile
}//Fragment_Search
