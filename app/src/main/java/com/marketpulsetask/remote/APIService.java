package com.marketpulsetask.remote;

import com.marketpulsetask.pojo.Response;
import io.reactivex.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface APIService {

    @GET("data")
    Observable<List<Response>> getData();

}
