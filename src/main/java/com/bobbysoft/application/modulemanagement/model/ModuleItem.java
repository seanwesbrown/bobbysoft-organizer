package com.bobbysoft.application.modulemanagement.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.time.DateUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.function.Function;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BaseModuleItem.class),
        @JsonSubTypes.Type(value = QuantityModuleItem.class)}
)
public abstract class ModuleItem<T> {
    private String label;
    private ModuleItemRepeatInterval repeatType;
    private Instant resetDate;
    private Instant lastUpdateTimestamp;

    public abstract ModuleItemType getModuleType();

    protected abstract void resetProgress();

    protected abstract void updateItemProgress(T progress);

    protected abstract boolean isItemComplete();

    public void updateProgress(T progress) {
        maybePerformIntervalReset();
        updateItemProgress(progress);
    }

    public boolean isComplete() {
        maybePerformIntervalReset();
        return isItemComplete();
    }

    protected void updateLastUpdateTimestamp() {
        lastUpdateTimestamp = Instant.now();
    }

    private void maybePerformIntervalReset() {
        Instant recentReset = getMostRecentReset();

        if (recentReset != null && lastUpdateTimestamp.isBefore(recentReset)) {
            resetProgress();
        }
    }

    public Instant getMostRecentReset() {
        if (resetDate == null || repeatType == null || repeatType == ModuleItemRepeatInterval.NONE) {
            return null;
        }

        return repeatType.mostRecentResetDate(resetDate);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ModuleItemRepeatInterval getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(ModuleItemRepeatInterval repeatType) {
        this.repeatType = repeatType;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public void setLastUpdateDate(Instant completedDate) {
        this.lastUpdateTimestamp = completedDate;
    }

    public Instant getLastUpdatedTimestamp() {
        return lastUpdateTimestamp;
    }

    public enum ModuleItemRepeatInterval {
        NONE((instant) -> instant),
        DAILY((instant) -> {
            int days = getPeriod(instant).getDays();
            return DateUtils.addDays(Date.from(instant), days).toInstant();
        }),
        WEEKLY((instant) -> {
            int days = getPeriod(instant).getDays();
            days = days - (days % 7);

            return DateUtils.addDays(Date.from(instant), days).toInstant();
        }),
        MONTHLY((instant) -> {
            int months = getPeriod(instant).getMonths();
            return DateUtils.addMonths(Date.from(instant), months).toInstant();
        });

        private final Function<Instant, Instant> incrementor;

        ModuleItemRepeatInterval(Function<Instant, Instant> incrementor) {
            this.incrementor = incrementor;
        }

        public Instant mostRecentResetDate(Instant resetDate) {
            return incrementor.apply(resetDate);
        }

        private static Period getPeriod(Instant instant) {
            LocalDate resetDate = LocalDate.ofInstant(instant, Clock.systemDefaultZone().getZone());
            return Period.between(resetDate, LocalDate.now());
        }
    }

    public enum ModuleItemType {
        BASE,
        QUANTITY
    }
}
