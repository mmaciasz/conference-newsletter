package pl.com.pollub.task.auxiliary;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mmaciasz on 2016-06-14.
 */
public enum DataGatherGroups {
    DAILY(Arrays.asList(
            DataGatherer.NEW,
            DataGatherer.CHANGES_FIELDS,
            DataGatherer.CHANGES_MAGAZINE,
            DataGatherer.CHANGES_OTHERS
    )),
    PAST(Arrays.asList(
            DataGatherer.NEW,
            DataGatherer.CHANGES_COMMENT,
            DataGatherer.CHANGES_FIELDS,
            DataGatherer.CHANGES_FILE,
            DataGatherer.CHANGES_MAGAZINE,
            DataGatherer.CHANGES_OTHERS
    )),
    FUTURE(Arrays.asList(
            DataGatherer.EXPIRE_DEADLINES,
            DataGatherer.NEARLY_START,
            DataGatherer.FULL_TEXT_DEADLINES,
            DataGatherer.PAYMENT_DEADLINES,
            DataGatherer.LAST_YEAR,
            DataGatherer.LAST_YEAR_STARTS
    ));

    private List<DataGatherer> dataPacks;

    DataGatherGroups(List<DataGatherer> dataPacks) {
        this.dataPacks = dataPacks;
    }

    public List<DataGatherer> getDataPacks() {
        return dataPacks;
    }
}
