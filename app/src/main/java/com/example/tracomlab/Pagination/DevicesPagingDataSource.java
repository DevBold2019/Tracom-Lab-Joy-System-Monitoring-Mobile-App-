package com.example.tracomlab.Pagination;

import android.app.Application;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracomlab.ConnectionToRest.RetrofitInterface.Atlas_Devices_Interface;
import com.example.tracomlab.Fragments.DevicesFragment;
import com.example.tracomlab.Model_Classes.Devices_Model;

import retrofit2.Retrofit;

public class DevicesPagingDataSource extends PageKeyedDataSource<Long, Devices_Model> {

    Atlas_Devices_Interface atlas_devices_interface;
    Application application;


    public DevicesPagingDataSource(Atlas_Devices_Interface atlas_devices_interface, Application application) {
        this.atlas_devices_interface = atlas_devices_interface;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Devices_Model> callback) {



    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Devices_Model> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Devices_Model> callback) {

    }
}
