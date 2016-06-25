package com.rhg.qf.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rhg.qf.R;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.mvp.api.QFoodApi;
import com.rhg.qf.mvp.presenter.UploadAndSaveImagePresenter;
import com.rhg.qf.mvp.presenter.UploadAndSaveImagePresenterImpl;
import com.rhg.qf.utils.AccountUtil;
import com.rhg.qf.utils.DataUtil;
import com.rhg.qf.utils.ImageUtils;
import com.rhg.qf.utils.ToastHelper;
import com.rhg.qf.widget.CircleImageView;
import com.rhg.qf.widget.ModifyHeadImageDialog;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:跑腿员信息页面
 * author：remember
 * time：2016/5/28 16:13
 * email：1013773046@qq.com
 */
public class DeliverInfoActivity extends BaseActivity implements ModifyHeadImageDialog.ChoosePicListener {
    /**
     * desc:裁剪图片
     * author：remember
     * time：2016/5/31 22:28
     * email：1013773046@qq.com
     */
    private static final String CROP = "com.android.camera.action.CROP";
    Button bt_save;
    Button bt_exit;
    UploadAndSaveImagePresenter uploadAndSaveImagePresenter;
    String imageStr = "";
    String userID = "19216801";
    String passWord = "123";
    Uri fileUri = null;
    @Bind(R.id.et_name_wrap)
    TextInputLayout etNameWrap;
    @Bind(R.id.tb_right_tv)
    TextView tbRightTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.et_id_wrap)
    TextInputLayout etIdWrap;
    @Bind(R.id.et_phone_wrap)
    TextInputLayout etPhoneWrap;
    @Bind(R.id.et_place_wrap)
    TextInputLayout etPlaceWrap;
    @Bind(R.id.ci_head)
    CircleImageView headView;
    @Bind(R.id.fl_tab)
    FrameLayout tb_common;

    @Override
    protected int getLayoutResId() {
        return R.layout.deliver_info_activity;
    }

    @Override
    protected void initView(View view) {
    }

    protected void initData() {
        uploadAndSaveImagePresenter = new UploadAndSaveImagePresenterImpl(this);
        tb_common.setBackgroundResource(R.color.colorActiveGreen);
        tbRightTv.setText("编辑");
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        etNameWrap.setError("");
        imageStr = AccountUtil.getInstance().getHeadImageUrl();
        /*从本地获取头像URI*/
//        if (AccountUtil.getInstance().hasAccount()) {
        if (!"".equals(imageStr)) {
            ImageLoader.getInstance().displayImage(imageStr, headView);
        } else {
            imageStr = QFoodApi.BASE_URL + "Pic/ClientPic/" +
                    /*AccountUtil.getInstance().getUserID()*/userID + ".jpg";
            ImageLoader.getInstance().displayImage(imageStr, headView);
            AccountUtil.getInstance().setHeadImageUrl(imageStr);
        }
         /*}else {
            ToastHelper.getInstance()._toast("请登录");
        }*/
    }

    @Override
    protected void showSuccess(Object s) {
        ImageLoader.getInstance().displayImage((String) s, headView);
    }

    @Override
    protected void showError(Object s) {

    }


    @Override
    final public void chooseFromGallery() {
        Intent intentFromGallery = new Intent();
        intentFromGallery.addCategory(Intent.CATEGORY_OPENABLE);
        intentFromGallery.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intentFromGallery.setAction(Intent.ACTION_OPEN_DOCUMENT);
            startActivityForResult(intentFromGallery, AppConstants.CODE_GALLERY_REQUEST_KITKAT);
        } else {
            intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intentFromGallery, AppConstants.CODE_GALLERY_REQUEST);
        }
    }

    @Override
    final public void chooseFromCamera() {
        Intent intentFromCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = Uri.fromFile(new File(AppConstants.f_Path, DataUtil.getCurrentTime())); // create a file to save the image
        intentFromCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        startActivityForResult(intentFromCamera, AppConstants.CODE_CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED) {
            ToastHelper.getInstance()._toast("CANCEL");
            return;
        }
        switch (requestCode) {
            case AppConstants.CODE_GALLERY_REQUEST:
                cropRawPic(data.getData());
                break;
            case AppConstants.CODE_GALLERY_REQUEST_KITKAT:
                String _path = getImagePath(data.getData());
                if (_path == null) break;
                File file = new File(_path);
                cropRawPic(Uri.fromFile(file));
                break;
            case AppConstants.CODE_CAMERA_REQUEST:
                cropRawPic(fileUri);
                break;
            case AppConstants.CODE_RESULT_REQUEST:
                Bitmap photo = data.getExtras().getParcelable("data");
                Uri _uri = ImageUtils.getImageUri(photo);
                /*TODO 将图片传服务器，服务器返回相应的URL，保存本地*/
                ImageLoader.getInstance().displayImage(_uri.toString(), headView);
                File _file = new File(_uri.getPath());
                /*if (_file.exists()) {
                    Log.i("RHG", "文件存在" + _file.getName());
                } else
                    Log.i("RHG", "文件不存在");*/
                uploadAndSaveImagePresenter.UploadAndSaveImage(_file, userID, passWord);
                ImageLoader.getInstance().clearMemoryCache();
                ImageLoader.getInstance().clearDiskCache();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Nullable
    private String getImagePath(Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKitKat && DocumentsContract.isDocumentUri(this, uri)) {//TODO 可能会导致内存溢出
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type))
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads")
                        , Long.valueOf(id));
                return getDataColumn(this, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("vedio".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(this, contentUri, selection, selectionArgs);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(this, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    @Nullable
    private String getDataColumn(Context context, Uri contentUri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = MediaStore.Images.ImageColumns.DATA;
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(contentUri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndex(column);
                return cursor.getString(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private void cropRawPic(Uri data) {
        Intent intent = new Intent(CROP);
        intent.setDataAndType(data, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, AppConstants.CODE_RESULT_REQUEST);
    }


    @OnClick({R.id.bt_save, R.id.bt_exit, R.id.tb_left_iv, R.id.ci_head, R.id.tb_right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.bt_save:
                break;
            case R.id.bt_exit:
                finish();
                break;
            case R.id.ci_head:
                ModifyHeadImageDialog modifyHeadImageDialog = new ModifyHeadImageDialog(this);
                modifyHeadImageDialog.show();
                modifyHeadImageDialog.setChoosePicListener(this);
                break;
            case R.id.tb_right_tv:
                break;
        }
    }
}