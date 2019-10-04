package com.pomden.apomden.Services;

import com.pomden.apomden.Models.Transfer;

import java.util.List;

public interface TransferDetailsResponser {

    void onSuccess(List<Transfer> transfers);
    void onFailed(String string);
}
