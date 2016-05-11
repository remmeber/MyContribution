package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.model.ShoppingCartBean;
import com.example.rhg.outsourcing.utils.ShoppingCartUtil;

import java.util.List;

/**
 * Created by remember on 2016/5/10.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    List<ShoppingCartBean> mData;
    Context context;

    public void setmData(List<ShoppingCartBean> mData) {
        this.mData = mData;
        setDataChange();
    }

    //TODO-------------------------------开放监听口-------------------------------------------------
    public View.OnClickListener getShortCartListener() {
        return ShortCartListener;
    }

    public ExpandableListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData == null ? 0 : mData.get(groupPosition).getGoods() == null
                ? 0 : mData.get(groupPosition).getGoods().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mData == null ? null : mData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mData == null ? null : mData.get(groupPosition).getGoods() == null
                ? null : mData.get(groupPosition).getGoods().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_elv_group_test, null);
            groupViewHolder.btGroupCheck = (ImageView) convertView.findViewById(R.id.ivCheckGroup);
            groupViewHolder.tvShopName = (TextView) convertView.findViewById(R.id.tvShopNameGroup);
            groupViewHolder.btForwardShop = (ImageView) convertView.findViewById(R.id.imaShopForwardGroup);
            convertView.setTag(groupViewHolder);
        } else
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        ShoppingCartUtil.checkItem(mData.get(groupPosition).isGroupSelected(), groupViewHolder.btGroupCheck);
        groupViewHolder.tvShopName.setText(mData.get(groupPosition).getMerchantName());
        groupViewHolder.btGroupCheck.setOnClickListener(ShortCartListener);
        groupViewHolder.btForwardShop.setOnClickListener(ShortCartListener);

        groupViewHolder.btGroupCheck.setTag(groupPosition);//TODO 设置点击的TAG，便于区分
        groupViewHolder.btForwardShop.setTag(groupPosition);//TODO 设置点击的TAG，便于区分
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_elv_child_test, null);
            childViewHolder.btGoodsCheck = (ImageView) convertView.findViewById(R.id.ivCheckGood);
            childViewHolder.goodsLogo = (ImageView) convertView.findViewById(R.id.ivGoodsLogo);
            childViewHolder.tvGoodsName = (TextView) convertView.findViewById(R.id.tvItemChild);
            childViewHolder.tvGoodsPrice = (TextView) convertView.findViewById(R.id.tvPriceNew);
            childViewHolder.btReduceNum = (ImageView) convertView.findViewById(R.id.ivReduce);
            childViewHolder.goodsCount = (TextView) convertView.findViewById(R.id.tvNum);
            childViewHolder.btAddNum = (ImageView) convertView.findViewById(R.id.ivAdd);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        ShoppingCartBean.Goods goods = mData.get(groupPosition).getGoods().get(childPosition);
        boolean isChildSelected = goods.isChildSelected();
        String goodsprice = "￥" + goods.getPrice();//TODO 获得商品的价格
        String goodsNum = goods.getNumber();//TODO 获得商品的数量
        String goodsName = goods.getGoodsName();//TODO 获得商品的名字
        int goodsLogoUrl = goods.getGoodsLogoUrl();//TODO 获得商品图片的链接

        childViewHolder.btGoodsCheck.setTag(groupPosition + "," + childPosition);
        childViewHolder.goodsLogo.setImageDrawable(context.getResources().getDrawable(goodsLogoUrl));
        childViewHolder.goodsLogo.setDrawingCacheEnabled(true);
        childViewHolder.tvGoodsName.setText(goodsName);
        childViewHolder.tvGoodsPrice.setText(goodsprice);
        childViewHolder.goodsCount.setText(goodsNum);

        childViewHolder.btGoodsCheck.setTag(groupPosition + "," + childPosition);
        childViewHolder.btReduceNum.setTag(goods);
        childViewHolder.btAddNum.setTag(goods);
        ShoppingCartUtil.checkItem(isChildSelected, childViewHolder.btGoodsCheck);

        childViewHolder.btGoodsCheck.setOnClickListener(ShortCartListener);
        childViewHolder.btReduceNum.setOnClickListener(ShortCartListener);
        childViewHolder.btAddNum.setOnClickListener(ShortCartListener);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        ImageView btGroupCheck;
        TextView tvShopName;
        ImageView btForwardShop;
    }

    class ChildViewHolder {
        /*商品选中*/
        ImageView btGoodsCheck;
        /*商品图片*/
        ImageView goodsLogo;
        /*商品的名字*/
        TextView tvGoodsName;
        /*商品的价格*/
        TextView tvGoodsPrice;
        /*减少商品*/
        ImageView btReduceNum;
        /*增加商品*/
        ImageView btAddNum;
        /*商品数量*/
        TextView goodsCount;
    }

    //TODO--------------------------购物车事件监听--------------------------------------------------
    View.OnClickListener ShortCartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int clickPosition;
            switch (v.getId()) {
                //TODO fragment中结算结算按钮
                case R.id.tvCountGoods:
                    if (ShoppingCartUtil.hasSelectedGoods(mData))
                        Toast.makeText(context, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "亲，请选择商品！", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivCheckGroup:
                    clickPosition = Integer.parseInt(String.valueOf(v.getTag()));
//                    isSelectAll = ShoppingCartUtil.selectGroup(mData,position);//TODO 如果有全选，则需要加上返回
                    ShoppingCartUtil.selectGroup(mData, clickPosition);
                    setDataChange();
                    notifyDataSetChanged();
                    break;
                case R.id.ivCheckGood:
                    String tag = String.valueOf(v.getTag());
                    if (tag.contains(",")) {
                        String s[] = tag.split(",");
                        int groupPosition = Integer.parseInt(s[0]);
                        int childPosition = Integer.parseInt(s[1]);
                        ShoppingCartUtil.selectOne(mData, groupPosition, childPosition);
                        setDataChange();
                        notifyDataSetChanged();
                    }
                    break;
                case R.id.imaShopForwardGroup:
                    clickPosition = (int) v.getTag();
                    Toast.makeText(context, "group " + clickPosition, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivAdd:
                    ShoppingCartUtil.addOrReduceGoodsNum(true, (ShoppingCartBean.Goods) v.getTag(),
                            (TextView) ((View) (v.getParent())).findViewById(R.id.tvNum));
                    setDataChange();
                    break;
                case R.id.ivReduce:
                    ShoppingCartUtil.addOrReduceGoodsNum(false, (ShoppingCartBean.Goods) v.getTag(),
                            (TextView) ((View) (v.getParent())).findViewById(R.id.tvNum));
                    setDataChange();
                    break;
            }
        }


    };

    private void setDataChange() {
        String[] infos = ShoppingCartUtil.getShoppingCount(mData);
        if (onDataChangeListener != null)
            onDataChangeListener.onDataChange(infos[1]);
    }

    private DataChangeListener onDataChangeListener;

    public void setDataChangeListener(DataChangeListener onDataChangeListener) {
        this.onDataChangeListener = onDataChangeListener;
    }

    public interface DataChangeListener {
        public void onDataChange(String CountMoney);
    }
}
