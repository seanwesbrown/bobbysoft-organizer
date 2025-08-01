package com.bobbysoft.application.modulemanagement.domain;

import org.apache.commons.lang3.time.DateUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.function.Function;

public abstract class ModuleItem<T> {
    private String label;
    private ModuleItemRepeatInterval repeatType;
    private Instant resetDate;
    private Instant completedDate;

    public abstract ModuleItemType getModuleType();

    public abstract boolean isComplete();

    public abstract void updateProgress(T progress);

    protected abstract void resetProgress();

    public void maybePerformIntervalReset() {
        Instant recentReset = getMostRecentReset();

        if (recentReset != null && completedDate.isBefore(recentReset)) {
            resetProgress();
            completedDate = null;
        }
    }

    protected void updateCompletionTimestamp(boolean isComplete) {
        if (isComplete) {
            completedDate = Instant.now();
        } else {
            completedDate = null;
        }
    }

    private Instant getMostRecentReset() {
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

    public Instant getCompletedDate() {
        return completedDate;
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
