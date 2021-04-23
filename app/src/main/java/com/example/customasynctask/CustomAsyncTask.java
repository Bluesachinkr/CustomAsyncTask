package com.example.customasynctask;

import android.os.Handler;
import android.os.Looper;

public abstract class CustomAsyncTask<S, T, U> {
    private Handler handler;

    CustomAsyncTask() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    protected abstract void onPreExecute();

    protected abstract U doInBackground(S... s);

    protected abstract void onPostExecute(U u);

    public void execute(S... s) {
        onPreExecute();
        Thread thread  = new Thread(new Runnable() {
            @Override
            public void run() {
                U u = null;
                try {
                    u = doInBackground(s);
                } finally {
                    final U n = u;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPostExecute(n);
                        }
                    });
                }
            }
        });
        thread.start();
    }
}
