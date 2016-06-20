package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.rhg.outsourcing.MeiTuanActivity;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.QFoodGridViewAdapter;
import com.example.rhg.outsourcing.bean.FavorableFoodUrlBean;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:todo 未使用fm
 *author：remember
 *time：2016/5/28 16:49
 *email：1013773046@qq.com
 */
public class TypeFragment extends Fragment {

    private int[] mImageResId;
    private int mCount;
    private String[] mTitle;
    private int mStart;
    private GridView mGridView;
    QFoodGridViewAdapter mDPGridViewAdapter;
    private List<FavorableFoodUrlBean> mDataList = new ArrayList<FavorableFoodUrlBean>();

    public TypeFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mImageResId = arguments.getIntArray(MeiTuanActivity.KEY_IMAGE);
            mTitle = arguments.getStringArray(MeiTuanActivity.KEY_TITLE);
            mCount = arguments.getInt(MeiTuanActivity.KEY_COUNT);
            mStart = arguments.getInt(MeiTuanActivity.KEY_START);
        }
        initData();

    }

    private void initData() {
        for (int i = mStart*10; i < mStart*10+mCount; i++) {
//            FavorableFoodUrlBean model = new FavorableFoodUrlBean();
//            model.setImageId(mImageResId[i]);
//            model.setContent(mTitle[i]);
//            mDataList.add(model);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_type_layout, container, false);
        mGridView = (GridView) view.findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "index" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mGridView.setNumColumns(5);
        mDPGridViewAdapter = new QFoodGridViewAdapter(getActivity(), R.layout.item_grid_rcv);
        mGridView.setAdapter(mDPGridViewAdapter);
        return view;
    }
}
