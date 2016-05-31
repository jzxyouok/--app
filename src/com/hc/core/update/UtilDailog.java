package com.hc.core.update;

import com.hc.xiaobairent.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 对话框工具类
 * 
 * @ClassName: UtilDailog
 * @author 景庆超
 * @date 2014-11-19 下午1:42:44
 * 
 */
public class UtilDailog {
	private static ProgressDialog pd = null;

	public static void showProgressDialog(Context context, String message) {
		if (null == pd) {
			pd = new ProgressDialog(context);
		}
		pd.setTitle("温馨提示");
		pd.setMessage(message);
		pd.show();
	}

	public static void dismissProgressDialog() {
		if (null != pd && pd.isShowing()) {
			pd.dismiss();
		}
	}

	public static void showTipMessage(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	@SuppressWarnings("deprecation")
	public static void showAlertDialog(Context context, String title, String message) {
		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		View vDialog = LayoutInflater.from(context).inflate(R.layout.aim_dialog_alert_pop_up, null);
		alertDialog.setView(vDialog, 0, 0, 0, 0);

		TextView tvTitle = (TextView) vDialog.findViewById(R.id.tv_dialog_title);
		if (TextUtils.isEmpty(title)) {
			tvTitle.setVisibility(View.GONE);
		} else {
			tvTitle.setText(title);
		}

		TextView tvMessage = (TextView) vDialog.findViewById(R.id.tv_dialog_message);
		if (!TextUtils.isEmpty(message)) {
			tvMessage.setText(message);
		} else {
			tvMessage.setVisibility(View.INVISIBLE);
		}

		vDialog.findViewById(R.id.btn_dialog_confirm_submit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});

		alertDialog.setCancelable(true);

		alertDialog.show();
		alertDialog.getWindow().setLayout(android.view.WindowManager.LayoutParams.FILL_PARENT,
				android.view.WindowManager.LayoutParams.WRAP_CONTENT);
	}

	/**
	 * 确认对话框
	 * 
	 * @author jqc
	 * @date 2013-08-27
	 * @modify
	 */
	@SuppressWarnings("deprecation")
	public static AlertDialog showConfirmDialog(Context context, String okText, String title, String message,
			final DialogInterface.OnClickListener okClicked, final DialogInterface.OnClickListener cancelClicked) {

		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		View vDialog = LayoutInflater.from(context).inflate(R.layout.aim_dialog_confirm_pop_up, null);
		alertDialog.setView(vDialog, 0, 0, 0, 0);
		TextView tvTitle = (TextView) vDialog.findViewById(R.id.tv_dialog_title);
		if (title == null || title.equals("")) {
			tvTitle.setText("确认");
		} else {
			tvTitle.setText(title);
		}

		TextView tvMessage = (TextView) vDialog.findViewById(R.id.tv_dialog_message);
		if (message != null) {
			tvMessage.setText(message);
		}

		Button okBtn = (Button) vDialog.findViewById(R.id.btn_dialog_confirm_submit);
		okBtn.setText(okText);
		okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (okClicked == null) {

				} else {
					okClicked.onClick(alertDialog, DialogInterface.BUTTON_POSITIVE);
				}
			}
		});
		vDialog.findViewById(R.id.btn_dialog_confirm_cancel).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cancelClicked == null) {
					alertDialog.dismiss();
				} else {
					cancelClicked.onClick(alertDialog, DialogInterface.BUTTON_NEGATIVE);
				}
			}
		});

		alertDialog.setCancelable(true);
		// Showing Alert Message
		alertDialog.show();
		alertDialog.getWindow().setLayout(android.view.WindowManager.LayoutParams.FILL_PARENT,
				android.view.WindowManager.LayoutParams.WRAP_CONTENT);
		return alertDialog;
	}
}
