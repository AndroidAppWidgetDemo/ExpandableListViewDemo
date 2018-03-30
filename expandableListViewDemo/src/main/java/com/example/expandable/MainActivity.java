package com.example.expandable;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import com.data.ChildItemData;
import com.data.GroupItemData;

public class MainActivity extends Activity {

	// ExpandableListView
	private ExpandableListView mExpandableListView = null;
	// Adapter
	private MyExpandableListAdapter mExpandableListAdapter = null;

	/*
	 * data
	 */
	private ArrayList<GroupItemData> mGroupItemDataList = null;
	private ArrayList<List<ChildItemData>> mChildItemDataList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		setContentView(R.layout.main);

		//
		initData();

		//
		initUI();
	}

	/***
	 * InitData
	 */
	public void initData() {
		// Group data
		mGroupItemDataList = new ArrayList<GroupItemData>();
		// data
		GroupItemData group = null;
		for (int i = 0; i < 3; i++) {
			group = new GroupItemData();
			group.setTitle("group: " + i);
			mGroupItemDataList.add(group);
		}

		// child data
		mChildItemDataList = new ArrayList<List<ChildItemData>>();
		for (int i = 0; i < mGroupItemDataList.size(); i++) {
			// child list
			ArrayList<ChildItemData> childItemDataList = new ArrayList<ChildItemData>();
			for (int j = 0; j < 3; j++) {
				ChildItemData childItemData = new ChildItemData();
				childItemData.setName("Group: " + i + " Child: " + j);
				childItemData.setAge(30);
				childItemData.setAddress("aaa: " + j);
				childItemDataList.add(childItemData);
			}
			mChildItemDataList.add(childItemDataList);
		}
	}

	/**
	 * initUI
	 */
	public void initUI() {

		// expandable ListView
		mExpandableListView = (ExpandableListView) findViewById(R.id.expandablelist);
		// adapter
		mExpandableListAdapter = new MyExpandableListAdapter(this,
				mGroupItemDataList, mChildItemDataList);
		// set adapter
		mExpandableListView.setAdapter(mExpandableListAdapter);

		
		//
		mExpandableListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Toast.makeText(
						MainActivity.this,
						mChildItemDataList.get(groupPosition)
								.get(childPosition).getName(),
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		// Group
		mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});

	}

}
