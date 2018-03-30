package com.example.expandable;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.data.ChildItemData;
import com.data.GroupItemData;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
	// view
	private Context mContext;
	private LayoutInflater mInflater;
	// data list
	private ArrayList<GroupItemData> mGroupItemDataList;
	private ArrayList<List<ChildItemData>> mChildItemDataList;

	public MyExpandableListAdapter(Context context,
			ArrayList<GroupItemData> groupItemDataList,
			ArrayList<List<ChildItemData>> childItemDataList) {
		this.mContext = context;
		mInflater = LayoutInflater.from(context);

		// data
		this.mGroupItemDataList = groupItemDataList;
		this.mChildItemDataList = childItemDataList;

	}

	@Override
	public int getGroupCount() {
		return mGroupItemDataList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mChildItemDataList.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mGroupItemDataList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mChildItemDataList.get(groupPosition).get(childPosition);
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
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// create view
		GroupHolder groupHolder = null;
		if (convertView == null) {
			groupHolder = new GroupHolder();
			convertView = mInflater.inflate(R.layout.expandablelist_group_item,
					null);
			groupHolder.groupItemTitleTextView = (TextView) convertView
					.findViewById(R.id.group_item_title_textView);
			groupHolder.groupItemImageView = (ImageView) convertView
					.findViewById(R.id.group_item_imageView);
			convertView.setTag(groupHolder);
		} else {
			groupHolder = (GroupHolder) convertView.getTag();
		}

		/*
		 * data
		 */
		//
		groupHolder.groupItemTitleTextView.setText(mGroupItemDataList.get(
				groupPosition).getTitle());
		// ture is Expanded or false is not isExpanded
		if (isExpanded) {
			groupHolder.groupItemImageView
					.setImageResource(R.drawable.expanded);
		} else {
			groupHolder.groupItemImageView
					.setImageResource(R.drawable.collapse);
		}
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// create view
		ChildHolder childHolder = null;
		if (convertView == null) {
			childHolder = new ChildHolder();
			convertView = mInflater.inflate(R.layout.expandablelist_child_item,
					null);

			childHolder.childItemNameTextView = (TextView) convertView
					.findViewById(R.id.child_item_name_textView);
			childHolder.childItemAgeTextView = (TextView) convertView
					.findViewById(R.id.child_item_age_textView);
			childHolder.childItemAddressTextView = (TextView) convertView
					.findViewById(R.id.child_item_address_textView);
			childHolder.childItemImageView = (ImageView) convertView
					.findViewById(R.id.child_item_image_textView);

			convertView.setTag(childHolder);
		} else {
			childHolder = (ChildHolder) convertView.getTag();
		}

		/*
		 * data
		 */
		childHolder.childItemNameTextView.setText(mChildItemDataList
				.get(groupPosition).get(childPosition).getName());
		childHolder.childItemAgeTextView.setText(mChildItemDataList
				.get(groupPosition).get(childPosition).getAge()
				+ "");
		childHolder.childItemAddressTextView.setText(mChildItemDataList
				.get(groupPosition).get(childPosition).getAddress());

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	/**
	 * 
	 * @author Holder
	 * 
	 */
	public static final class GroupHolder {
		TextView groupItemTitleTextView = null;
		ImageView groupItemImageView = null;
	}

	public static final class ChildHolder {
		TextView childItemNameTextView;
		TextView childItemAgeTextView;
		TextView childItemAddressTextView;
		ImageView childItemImageView;
	}

}
