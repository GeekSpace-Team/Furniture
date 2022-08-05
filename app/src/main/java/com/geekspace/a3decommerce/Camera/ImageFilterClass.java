package com.geekspace.a3decommerce.Camera;

import com.otaliastudios.cameraview.filter.Filter;

public class ImageFilterClass {
    String filterName;
    int filterImage;
    Filter filter;


    public ImageFilterClass(String filterName, int filterImage, Filter filter) {
        this.filterName = filterName;
        this.filterImage = filterImage;
        this.filter = filter;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public int getFilterImage() {
        return filterImage;
    }

    public void setFilterImage(int filterImage) {
        this.filterImage = filterImage;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
