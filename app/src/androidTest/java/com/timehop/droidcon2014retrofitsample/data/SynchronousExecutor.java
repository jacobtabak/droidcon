package com.timehop.droidcon2014retrofitsample.data;

import android.util.Log;

import java.util.concurrent.Executor;

public class SynchronousExecutor implements Executor {
  @Override
  public void execute(Runnable runnable) {
    try {
      runnable.run();
    } catch (Exception e){
      Log.e("Fail", "fail", e);
    }
  }
}
