package com.zzl.top.baseadapter.list;

/**
 * Created by zhangzl
 * desc:
 * date: 2016/10/13.
 */

public interface ItemViewDelegate<T> {
    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ViewHolder holder, T t, int position);
}
