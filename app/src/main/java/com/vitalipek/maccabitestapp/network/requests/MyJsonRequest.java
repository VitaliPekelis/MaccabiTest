package com.vitalipek.maccabitestapp.network.requests;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.vitalipek.maccabitestapp.network.utils.Mapper;

import java.io.UnsupportedEncodingException;


/**
 * Created by vitaliP on 12/01/2016.
 */
public class MyJsonRequest<T> extends JsonRequest<T>
{
    private static final String TAG = MyJsonRequest.class.getSimpleName();
	private Class<T> responseType;

	/**
	 * Creates a new request.
	 *
	 * @param method
	 *            the HTTP method to use
	 * @param url
	 *            URL to fetch the JSON from
	 * @param requestData
	 *            A {@link Object} to post and convert into json as the request. Null is allowed and indicates no parameters will be posted along with request.
	 * @param responseType
	 *            A {@link Class} of response Object
	 * @param listener
	 *            Listener to receive the JSON response
	 * @param errorListener
	 *            Error listener, or null to ignore errors.
	 */
	public MyJsonRequest(int method, String url, Object requestData, Class<T> responseType, Response.Listener<T> listener, Response.ErrorListener errorListener)
	{
		super(method, url, (requestData == null) ? null : Mapper.string(requestData), listener, errorListener);
		this.responseType = responseType;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response)
	{

        Log.d(TAG, "parseNetworkResponse() response.statusCode = " +response.statusCode);

		String jsonString = null;

		try
		{
			jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

			T jsonObject = Mapper.object(jsonString, responseType);
			return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
