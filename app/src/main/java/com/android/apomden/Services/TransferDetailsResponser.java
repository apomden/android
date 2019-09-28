package com.android.apomden.Services;

import com.android.apomden.Models.Department;
import com.android.apomden.Models.Transfer;

import java.util.List;

public interface TransferDetailsResponser {

    void onSuccess(List<Transfer> transfers);
    void onFailed(String string);
}
