package com.ocean.driver.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ocean.driver.R;
import com.ocean.driver.api.BaseUrl;
import com.ocean.driver.api.HttpUtil;
import com.ocean.driver.entity.ApiResponse;
import com.ocean.driver.tools.PreferenceUtils;
import com.ocean.driver.tools.ToastUtil;
import com.ocean.driver.tools.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
import static com.ocean.driver.tools.Utils.createQRCodeBitmap;


/**
 * 驳回操作单原因
 */
public class QrCodeDialog extends Dialog {


    ImageView iv_qrcode;
    private Context context;
    private String content;

    public QrCodeDialog(Context context) {
        super(context);
    }

    public QrCodeDialog(Context context, int theme, String content) {
        super(context, theme);
        this.context = context;
        this.content = content;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_qr_code);
        initView();
    }

    private void initView() {
        iv_qrcode=findViewById(R.id.iv_qrcode);
//        Bitmap qrCodeBitmap = Utils.createQRCodeBitmap(content, 300, 300, "UTF-8", "H", "1", Color.GREEN, WHITE);
        Bitmap qrCodeBitmap = createQRCodeBitmap(content);
    iv_qrcode.setImageBitmap(qrCodeBitmap);

    }

}
