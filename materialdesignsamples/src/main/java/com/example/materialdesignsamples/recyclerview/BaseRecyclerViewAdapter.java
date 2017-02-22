package com.example.materialdesignsamples.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zf on 17-2-21.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    private Context mContext;
    protected List<T> mData;
    private int mDefaultLayoutId = 0;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public BaseRecyclerViewAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
    }

    public BaseRecyclerViewAdapter(Context context, int defaultLayoutId, List<T> data) {
        this(context, data);
        mDefaultLayoutId = defaultLayoutId;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDefaultLayoutId == 0) {
            throw new RuntimeException("请在 " + this.getClass().getSimpleName() + " 中重写 getItemViewType " +
                    "方法返回布局资源 id，或者使用 " + this.getClass().getSimpleName() +
                    " 三个参数的构造方法 LQRAdapterForRecyclerView(Context context, int defaultLayoutId, List<T> data)");
        }
        return mDefaultLayoutId;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(mContext, LayoutInflater.from(mContext).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        convert(holder, mData.get(position), position);
    }

    public abstract void convert(BaseRecyclerViewHolder helper, T item, int position);

    @Override
    public int getItemCount() {
        if (mData != null)
            return mData.size();
        return 0;
    }

    /**
     * 获取指定索引位置的数据模型
     */
    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * 获取数据集合
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合）
     */
    public void addNewData(List<T> data) {
        if (data != null) {
            mData.addAll(0, data);
            notifyItemRangeInserted(0, data.size());
        }
    }

    /**
     * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合）
     */
    public void addMoreData(List<T> data) {
        if (data != null) {
            mData.addAll(mData.size(), data);
            notifyItemRangeInserted(mData.size(), data.size());
        }
    }

    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据列表）
     */
    public void setData(List<T> data) {
        if (null == data) {
            mData.clear();
        } else {
            if (null == mData) {
                mData = data;
            } else {
                mData.clear();
                mData.addAll(data);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据列表
     */
    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除指定索引数据条目
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 删除指定数据条目
     */
    public void removeItem(T model) {
        removeItem(mData.indexOf(model));
    }

    /**
     * 在指定位置添加数据条目
     */
    public void addItem(int position, T model) {
        mData.add(position, model);
        notifyItemInserted(position);
    }

    /**
     * 在集合头部添加数据条目
     */
    public void addFirstItem(T model) {
        addItem(0, model);
    }

    /**
     * 在集合末尾添加数据条目
     */
    public void addLastItem(T model) {
        addItem(mData.size(), model);
    }

    /**
     * 替换指定索引的数据条目
     */
    public void setItem(int position, T newModel) {
        mData.set(position, newModel);
        notifyItemChanged(position);
    }

    /**
     * 替换指定数据条目
     */
    public void setItem(T oldModel, T newModel) {
        setItem(mData.indexOf(oldModel), newModel);
    }

    /**
     * 移动数据条目的位置
     */
    public void moveItem(int fromPosition, int toPosition) {
        notifyItemChanged(fromPosition);
        notifyItemChanged(toPosition);

        //要先执行上面的notifyItemChanged，然后再执行下面的moveItem事件

        mData.add(toPosition, mData.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 获取第一个数据模型
     */
    public T getFirstItem() {
        return getItemCount() > 0 ? getItem(0) : null;
    }

    /**
     * 得到最后一个数据模型
     */
    public T getLastItem() {
        return getItemCount() > 0 ? getItem(getItemCount() - 1) : null;
    }

    public interface OnItemClickListener{
        void onItemClick(RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener{
        boolean onItemLongCLick(RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.mOnItemLongClickListener = onItemLongClickListener;
    }
}
