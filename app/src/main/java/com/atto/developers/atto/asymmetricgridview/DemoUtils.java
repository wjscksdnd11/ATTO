package com.atto.developers.atto.asymmetricgridview;

import com.atto.developers.atto.networkdata.tradedata.TradeData;

import java.util.ArrayList;
import java.util.List;

public final class DemoUtils {
    public int currentOffset;

    public DemoUtils() {
    }

    public List<DemoItem> moreItems(int qty, List<TradeData> tradeData) {
        List<DemoItem> items = new ArrayList<>();

        for (int i = 0; i < qty; i++) {
            int colSpan = Math.random() < 0.2f ? 2 : 1;
            // Swap the next 2 lines to have items with variable
            // column/row span.
            // int rowSpan = Math.random() < 0.2f ? 2 : 1;
            int rowSpan = colSpan;
            DemoItem item = new DemoItem(colSpan, rowSpan, currentOffset + i, tradeData.get(i));
            items.add(item);
        }

        currentOffset += qty;

        return items;
    }
}
