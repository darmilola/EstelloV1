package com.estello.android.ViewModel;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * The backing data object for an {@link ExpandableGroup}
 */
public class ExpandableGroup<T extends Parcelable> implements Parcelable {
    private String divisionalType;
    private List<T> items;
    private String fileItemsCount;
    private String fileSize;
    private String sectionId;

    public ExpandableGroup(String divisionalType,String itemsCount,String fileSize,String sectionId, List<T> items) {
        this.divisionalType = divisionalType;
        this.items = items;
        this.sectionId = sectionId;
        this.fileSize = fileSize;
        this.fileItemsCount = itemsCount;
    }

    public String getDivisionalType() {
        return divisionalType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getSectionId() {
        return sectionId;
    }

    public String getFileItemsCount() {
        return fileItemsCount;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public void setDivisionalType(String divisionalType) {
        this.divisionalType = divisionalType;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setFileItemsCount(String fileItemsCount) {
        this.fileItemsCount = fileItemsCount;
    }

    public List<T> getItems() {
        return items;
    }

    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public String toString() {
        return "ExpandableGroup{" +
                "title='" + divisionalType + '\'' +
                ", items=" + items +
                '}';
    }

    protected ExpandableGroup(Parcel in) {
        divisionalType = in.readString();
        fileSize = in.readString();
        sectionId = in.readString();
        fileItemsCount = in.readString();
        byte hasItems = in.readByte();
        int size = in.readInt();
        if (hasItems == 0x01) {
            items = new ArrayList<T>(size);
            Class<?> type = (Class<?>) in.readSerializable();
            in.readList(items, type.getClassLoader());
        } else {
            items = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(divisionalType);
        dest.writeString(sectionId);
        dest.writeString(fileSize);
        dest.writeString(fileItemsCount);
        if (items == null) {
            dest.writeByte((byte) (0x00));
            dest.writeInt(0);
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(items.size());
            final Class<?> objectsType = items.get(0).getClass();
            dest.writeSerializable(objectsType);
            dest.writeList(items);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<ExpandableGroup> CREATOR =
            new Creator<ExpandableGroup>() {
                @Override
                public ExpandableGroup createFromParcel(Parcel in) {
                    return new ExpandableGroup(in);
                }

                @Override
                public ExpandableGroup[] newArray(int size) {
                    return new ExpandableGroup[size];
                }
            };
}