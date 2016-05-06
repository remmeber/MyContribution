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
import com.example.rhg.outsourcing.apapter.DPGridViewAdapter;
import com.example.rhg.outsourcing.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TypeFragment extends Fragment {

    private int[] mImageResId;
    private int mCount;
    private String[] mTitle;
    private int mStart;
    private GridView mGridView;
    DPGridViewAdapter mDPGridViewAdapter;
    private List<ImageModel> mDataList = new ArrayList<ImageModel>();

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
//            ImageModel model = new ImageModel();
//            model.setImageId(mImageResId[i]);
//            model.setContent(mTitle[i]);
//            mDataList.add(model);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclegridtype, container, false);
        mGridView = (GridView) view.findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "index" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mGridView.setNumColumns(5);
        mDPGridViewAdapter = new DPGridViewAdapter(getActivity(), mDataList, R.layout.recyclegriditem);
        mGridView.setAdapter(mDPGridViewAdapter);
        return view;
    }
}
