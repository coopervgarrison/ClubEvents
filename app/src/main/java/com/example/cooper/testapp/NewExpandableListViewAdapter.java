package com.example.cooper.testapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewExpandableListViewAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<String> groups;
    ArrayList<ArrayList<String>> children;

    public NewExpandableListViewAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<String>> children) {
        this.context = context;
        this.groups = groups;
        this.children = children;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.elvGroup);
        textView.setText(groups.get(groupPosition));
        textView.setPadding(100, 0, 0, 0);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(40);
        textView.setTypeface(Typeface.DEFAULT_BOLD, 2);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_child, null);
        }
        final TextView textView = (TextView) convertView.findViewById(R.id.elvChild);
        textView.setText(children.get(groupPosition).get(childPosition));
        textView.setPadding(100, 0, 0, 0);
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(20);
        textView.setTypeface(Typeface.DEFAULT, 2);
        final int groupP = groupPosition;
        final int childP = childPosition;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayEvent.class);
                intent.putExtra("event", children.get(groupP).get(childP));
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
