package com.marketpulsetask;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import com.marketpulsetask.databinding.ActivityDataListBinding;
import com.marketpulsetask.remote.ApiResponse;
import com.marketpulsetask.remote.RetrofitClient;
import com.marketpulsetask.utils.MyUtil;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class DataListActivity extends AppCompatActivity {

    private static final String TAG = "DataListActivity";
    ActivityDataListBinding biniding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        fetchData();
        biniding = DataBindingUtil.setContentView(this, R.layout.activity_data_list);
    }


    public void fetchData() {
        if (MyUtil.isNetworkConnected(this)) {
            RetrofitClient.getAPIService().getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<ApiResponse>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(TAG, "onSubscribe: ");
                        }

                        @Override
                        public void onNext(List<ApiResponse> responseModel) {
                            Log.d(TAG, "onNext: " + responseModel);
                            biniding.textView.setText(responseModel.get(0).getCriteria().get(0).getText());

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onComplete: ");
                        }
                    });

        }
    }
}
