package com.tekinarslan.material.sample.weight.wheelview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;

/**
 * Create by：ml_bright on 2015/7/10 17:46
 * Email: 2504509903@qq.com
 */
public class AddressView {

    private Context mContext;
    private String cityText;

    private int currentProvincePosition = 1;
    private int currentCityPosition = 0;

    private String mCity;
    private String mProvince;

    private OnCityTextChange onCityTextChange;


    public AddressView(Context context) {
        this.mContext = context;
    }

//    public AddressView(Context context, User user) {
//        this.mContext = context;
//        if(user != null) {
//            this.mCity = user.detail.city;
//            this.mProvince = user.detail.province;
//        }
//    }

    public void showAddressDialog() {
        AddressChooseDialog.show(mContext).setView(cityWheelView()).addOnDialogItemClickListener(new AddressChooseDialog.OnDialogItemClickListener() {
            @Override
            public void onClick(int which) {
                if (which == AddressChooseDialog.SAVE) {
                    if (onCityTextChange != null) {
                        onCityTextChange.onChange(cityText);
                    }
                }
            }
        });
    }

    private View cityWheelView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_city_wheel, null);
        final WheelView country = (WheelView) contentView.findViewById(R.id.wheel_city_country);
        country.setVisibleItems(2);
        country.setViewAdapter(new CountryAdapter(mContext));

        final String cities[][] = AddressData.CITIES;

        final WheelView city = (WheelView) contentView.findViewById(R.id.wheel_city_city);
        city.setVisibleItems(0);

        country.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateCities(city, cities, newValue);
                currentProvincePosition = country.getCurrentItem();
                currentCityPosition = city.getCurrentItem();
                mProvince = AddressData.PROVINCES[currentProvincePosition];
                mCity = AddressData.CITIES[currentProvincePosition][currentCityPosition];
                cityText = mProvince + "  " + mCity;
            }
        });

        city.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                currentCityPosition = city.getCurrentItem();
                currentProvincePosition = country.getCurrentItem();
                mProvince = AddressData.PROVINCES[currentProvincePosition];
                mCity = AddressData.CITIES[currentProvincePosition][currentCityPosition];
                cityText = mProvince + "  " + mCity;
            }
        });

        country.setCurrentItem(currentProvincePosition == 0 ? 1 : currentProvincePosition);// 设置北京
        city.setCurrentItem(currentProvincePosition == 0 ? 0 : currentCityPosition);
        return contentView;
    }

    public String getCity() {
        return mCity.equals("未设定") ? null : mCity;
    }

    public String getProvince() {
        return mProvince.equals("未设定") ? null : mProvince;
    }

    /**
     * Updates the city wheel
     */
    private void updateCities(WheelView city, String cities[][], int index) {
        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(mContext, cities[index]);
        adapter.setTextSize(18);
        city.setViewAdapter(adapter);
        city.setCurrentItem(0);
    }

    /**
     * Adapter for countries
     */
    private class CountryAdapter extends AbstractWheelTextAdapter {
        // Countries names
        private String countries[] = AddressData.PROVINCES;

        /**
         * Constructor
         */
        protected CountryAdapter(Context context) {
            super(context, R.layout.wheel_country_city, NO_RESOURCE);
            setItemTextResource(R.id.wheel_city_country_name);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return countries.length;
        }

        @Override
        protected CharSequence getItemText(int index) {
            return countries[index];
        }
    }

    public void setOnCityTextChange(OnCityTextChange onCityTextChange) {
        this.onCityTextChange = onCityTextChange;
    }

    public interface OnCityTextChange {
        void onChange(String text);
    }

}
