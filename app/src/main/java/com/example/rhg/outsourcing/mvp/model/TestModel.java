package com.example.rhg.outsourcing.mvp.model;

import com.example.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.example.rhg.outsourcing.bean.FavorableFoodUrlBean;
import com.example.rhg.outsourcing.bean.HomeBean;
import com.example.rhg.outsourcing.bean.RecommendListUrlBean;
import com.example.rhg.outsourcing.bean.TextTypeBean;
import com.example.rhg.outsourcing.mvp.api.QFoodApiMamager;
import com.example.rhg.outsourcing.mvp.api.QFoodApiService;

import rx.Observable;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;

/**
 * desc:mvp测试实现
 * author：remember
 * time：2016/5/28 17:00
 * email：1013773046@qq.com
 */
public class TestModel implements BaseModel {

    @Override
    public Observable<HomeBean> getData() {
        QFoodApiService qFoodApiService = QFoodApiMamager.getInstant().getQFoodApiService();
        return Observable.zip(qFoodApiService.getBannerUrl(), qFoodApiService.getFavorableFood(),
                qFoodApiService.getRecommendList(),qFoodApiService.getMessage(),
                new Func4<BannerTypeUrlBean, FavorableFoodUrlBean, RecommendListUrlBean,TextTypeBean, HomeBean>() {
                    @Override
                    public HomeBean call(BannerTypeUrlBean bannerTypeUrlBean,
                                         FavorableFoodUrlBean favorableFoodUrlBean,
                                         RecommendListUrlBean recommendListUrlBean,
                                         TextTypeBean textTypeBean
                    ) {
                        HomeBean _homeBean = new HomeBean();
                        _homeBean.setBannerEntityList(bannerTypeUrlBean.getRows());
                        _homeBean.setFavorableFoodEntityList(favorableFoodUrlBean.getRows());
                        _homeBean.setRecommendShopBeanEntityList(recommendListUrlBean.getRows());
                        _homeBean.setTextTypeBean(textTypeBean);
                        return _homeBean;
                    }
                });
        /*return QFoodApiMamager.getInstant().getQFoodApiService().getFavorableFood()
                .flatMap(new Func1<FavorableFoodUrlBean, Observable<List<FavorableFoodUrlBean.FavorableFoodEntity>>>() {
                    @Override
                    public Observable<List<FavorableFoodUrlBean.FavorableFoodEntity>> call(final FavorableFoodUrlBean favorableFoodBean) {
                        return Observable.create(new Observable.OnSubscribe<List<FavorableFoodUrlBean.FavorableFoodEntity>>() {
                            @Override
                            public void call(Subscriber<? super List<FavorableFoodUrlBean.FavorableFoodEntity>> subscriber) {
                                int _count = Integer.valueOf(favorableFoodBean.getTotal());
                                Log.i("RHG",""+_count);
                                List<FavorableFoodUrlBean.FavorableFoodEntity> rowsBeanList = new ArrayList<FavorableFoodUrlBean.FavorableFoodEntity>();
                                for (int i = 0; i < _count; i++) {
                                    rowsBeanList.add(favorableFoodBean.getRows().get(i));
                                }
                                subscriber.onNext(rowsBeanList);
                            }
                        });
                    }
                });*/
    }
}


   /* @Override
    public Observable<BannerTypeBean> getData() {
        return QFoodApiMamager.getInstant().getQFoodApiService().getBannerUrl()
                .flatMap(new Func1<BannerTypeUrlBean, Observable<BannerTypeBean>>() {// TODO: 类型转换
                    @Override
                    public Observable<BannerTypeBean> call(final BannerTypeUrlBean bannerTypeUrlBean) {
                        Log.i("RHG", "" + bannerTypeUrlBean.toString());
                        return Observable.create(new Observable.OnSubscribe<BannerTypeBean>() {
                            @Override
                            public void call(Subscriber<? super BannerTypeBean> subscriber) {
                                BannerTypeBean imageModel = new BannerTypeBean();
                                List<String> imageUrls = new ArrayList<String>();
                                int _count = Integer.valueOf(bannerTypeUrlBean.getTotal());
                                for (int i = 0; i < _count; i++) {
                                    String _imageurl = bannerTypeUrlBean.getRows().get(i).getSrc();
                                    imageUrls.add(_imageurl);
                                    Log.i("RHG", _imageurl);
                                }
                                imageModel.setImageUrls(imageUrls);
                                subscriber.onNext(imageModel);
                            }
                        });
                    }
                });
}*/
    /*@Override
    public Observable<Object> GetData() {
        return Observable.just("1").flatMap(new Func1<String, Observable<Object>>() {
            @Override
            public Observable<Object> call(String s) {
                if("1".equals(s))
                    return Observable.create(new Observable.OnSubscribe<Object>() {
                        @Override
                        public void call(final Subscriber<? super Object> subscriber) {
                            try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    subscriber.onNext("success");
                            *//*new Thread(){
                                @Override
                                public void run() {
                                    // TODO: 网络请求

                                }
                            }.start();*//*
                        }
                    });
                return null;
            }
        });
    }
}*/
