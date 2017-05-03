package com.viralandroid.tieproperty;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by T on 28-04-2017.
 */

public class CategoryPageAdapter extends BaseAdapter implements Filterable{
    LayoutInflater inflater;
    Context context;
    ArrayList<Category> categories;
    ArrayList<Category> mStringFilterList;
    ValueFilter valueFilter;


    public CategoryPageAdapter(Context context,ArrayList<Category> categories){
        this.context = context;
        this.categories = categories;
        inflater = LayoutInflater.from(context);
        mStringFilterList = categories;
    }



    public class ViewHolder {
        TextView name;
        CheckBox checkBox;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return categories.indexOf(getItem(i));
    }






    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final CategoryPageAdapter.ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.category_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.category_name);
            holder.checkBox = (CheckBox) view.findViewById(R.id.check_items);

            final Category category = categories.get(i);
            final ListView listView;

            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    category.setChecked(isChecked);
                    Log.e("checkd","checked true");
                    Log.e("print",category.getId());
                }
            });

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.name.setText(categories.get(i).title);
        holder.checkBox.setChecked(categories.get(i).getChecked());
        Log.e("message",categories.get(i).getChecked().toString());
        Log.e("message1",categories.get(i).getName());
        Log.e("id",categories.get(i).getId());
        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }


    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            Log.e("results",results.toString());

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Category> filterList = new ArrayList<Category>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        Category category = new Category(mStringFilterList.get(i)
                                .getName(), mStringFilterList.get(i)
                                .getId(), mStringFilterList.get(i));

                        filterList.add(category);
                        Log.e("results",results.toString());
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
                Log.e("results",filterList.toString());
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            categories = (ArrayList<Category>) results.values;
            notifyDataSetChanged();
        }
    }


    }
