package com.app.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.kymjs.aframe.utils.SystemTool;

import android.content.Context;

import com.base.service.impl.HttpAysnResultInterface;
import com.base.service.impl.HttpAysnTaskInterface;
import com.base.service.impl.HttpClientUtils;
import com.base.supertoasts.util.AppToast;
import com.beabox.hjy.tt.R;

public class DeleteMyCollectService implements HttpAysnTaskInterface {
	private Context context;
	private Integer mTag;// Action 标签
	private HttpAysnResultInterface callback ;
	public DeleteMyCollectService(Context context, Integer mTag,
			HttpAysnResultInterface callback) {
		super();
		this.context = context;
		this.mTag = mTag;
		this.callback = callback;
	}
	
	public void doDelete(String token,String action,String delete_type,String favorite_id){
		try{
			if(!SystemTool.checkNet(context)){
				AppToast.toastMsgCenter(context, context.getResources().getString(R.string.no_network)).show();
				return ;
			}
			
			String url=context.getResources().getString(R.string.delete_collection)+"?client=2";
			
			JSONObject param = new JSONObject() ;
			param.put("action", action);
			param.put("delete_type", delete_type);
			param.put("favorite_id", favorite_id);
			
			Map<String,String> mapHead = new HashMap<String,String>();
			mapHead.put("Authorization", "Token " + token);
			
			StringEntity bodyEntity = new StringEntity(param.toString(),"UTF-8");
			HttpClientUtils client = new HttpClientUtils();
			
			client.post_with_head_and_body(context, mTag, url, mapHead, bodyEntity, this);
			
			
			
		}catch(Exception e){
			
		}
	}

	@Override
	public void requestComplete(Object tag, int statusCode, Object header,
			Object result, boolean complete) {
		callback.dataCallBack(tag, statusCode, result);
	}
}
