package com.example.android.rxjava2sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.baseutils.ConvertUtils;
import com.example.baseutils.Utils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.bt);
        Button bt1 = (Button) findViewById(R.id.bt1);
        Button bt2 = (Button) findViewById(R.id.bt2);
        Button bt3 = (Button) findViewById(R.id.bt3);
        bt.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                Utils.init(this);
                Toast.makeText(this, "ConvertUtils.px2dp(1440):" + ConvertUtils.px2dp(1440), Toast.LENGTH_SHORT).show();
//                click();
                break;
            case R.id.bt1:
                clickChain();
                break;
            case R.id.bt2:
                clickOnNext();
                break;
            case R.id.bt3:
                clickAsync();
                break;
        }
    }

    /**
     * 分开写
     * 先创建一个发射数据方
     * 再创建一个接收数据方
     * 使用subscribe将两者关联起来
     *
     * ObservableEmitter    发射器
     * Disposable           单词的意思是一次性用品，用完即可丢弃，调用dispose()方法断开下游，上游还是会继续发送只是下游不接收了
     * CompositeDisposable.add()将Disposable添加到容器中
     * 在退出的时候调用CompositeDisposable.clear() 即可切断所有的水管
     */
    private void click() {
        //上游，也就是产生数据，发射数据方,被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        //下游,接收数据，观察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("MainActivity", "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d("MainActivity", "next " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MainActivity", "error");
            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "complete");
            }
        };

        //将上下游进行关联起来
        observable.subscribe(observer);
    }

    /**
     * 链式调用方法
     */
    private void clickChain() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(4);
                e.onNext(5);
                e.onNext(6);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("MainActivity", "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d("MainActivity", "next " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MainActivity", "error");
            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "complete");
            }
        });
    }

    /**
     * 只接收onNext事件
     */
    private void clickOnNext(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Log.d("MainActivity", "currentThread Name is " + Thread.currentThread().getName());
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("MainActivity", "integer:" + integer);
                Log.d("MainActivity", "currentThread Name is " + Thread.currentThread().getName());
            }
        };

        observable.subscribe(consumer);
    }

    /**
     * subscribeOn      指定上游发送事件的线程
     * observeOn        指定下游发送事件的线程
     *
     * Schedulers.io()  代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation()         代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread()           代表一个常规的新线程
     * AndroidSchedulers.mainThread()   代表Android的主线程
     */
    private void clickAsync(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d("MainActivity", "Observable currentThread Name is " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("MainActivity", "Observer currentThread Name is " + Thread.currentThread().getName());
                Log.d("MainActivity", "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d("MainActivity", "Observer currentThread Name is " + Thread.currentThread().getName());
                Log.d("MainActivity", "next " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MainActivity", "error");
            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "complete");
            }
        };

        observable.subscribeOn(Schedulers.newThread())//放到子线程中运行
                .observeOn(AndroidSchedulers.mainThread())//放到主线程中运行
                .subscribe(observer);
    }

}

