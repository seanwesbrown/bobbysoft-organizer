package com.bobbysoft.application.modulemanagement.model;

import com.bobbysoft.application.modulemanagement.utils.ModuleItemBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ModuleItemTest {

    @Nested
    @DisplayName("Tests for item completion")
    class IsComplete {
        @Test
        @DisplayName("Is complete returns true for a completed base item")
        public void testCompleteBaseItem() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withIsComplete(true)
                    .build();

            assert moduleItem.isComplete();
        }

        @Test
        @DisplayName("Is complete returns false for an incomplete base item")
        public void testIncompleteBaseItem() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withIsComplete(false)
                    .build();

            assert !moduleItem.isComplete();
        }

        @Test
        @DisplayName("Is complete returns true for a base item completed after reset")
        public void testBaseItemCompletedAfterReset() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withIsComplete(true)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.WEEKLY)
                    .withLastUpdateDate(Instant.now().minus(1, ChronoUnit.DAYS))
                    .withResetDate(Instant.now().minus(2, ChronoUnit.DAYS))
                    .build();

            assert moduleItem.isComplete();
        }

        @Test
        @DisplayName("Is complete returns false for an base item completed before reset")
        public void testBaseItemCompletedBeforeReset() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withIsComplete(true)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.WEEKLY)
                    .withLastUpdateDate(Instant.now().minus(2, ChronoUnit.DAYS))
                    .withResetDate(Instant.now().minus(1, ChronoUnit.DAYS))
                    .build();

            assert !moduleItem.isComplete();
        }

        @Test
        @DisplayName("Is complete returns true for a completed base item")
        public void testCompleteQuantityItem() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                    .withQuantity(100.0, 100.0)
                    .build();

            assert moduleItem.isComplete();
        }

        @Test
        @DisplayName("Is complete returns false for an incomplete base item")
        public void testIncompleteQuantityItem() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                    .withQuantity(50.0, 100.0)
                    .build();

            assert !moduleItem.isComplete();
        }


        @Test
        @DisplayName("Is complete returns true for a quantity item completed after reset")
        public void testQuantityItemCompletedAfterReset() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                    .withQuantity(100.0, 100.0)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.WEEKLY)
                    .withLastUpdateDate(Instant.now().minus(1, ChronoUnit.DAYS))
                    .withResetDate(Instant.now().minus(2, ChronoUnit.DAYS))
                    .build();

            assert moduleItem.isComplete();
            assert ((QuantityModuleItem) moduleItem).getCurrentQuantity().equals(100.0);
        }


        @Test
        @DisplayName("Is complete returns false for an quantity item completed after reset")
        public void testQuantityItemCompletedBeforeReset() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                    .withQuantity(100.0, 100.0)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.WEEKLY)
                    .withLastUpdateDate(Instant.now().minus(2, ChronoUnit.DAYS))
                    .withResetDate(Instant.now().minus(1, ChronoUnit.DAYS))
                    .build();

            assert !moduleItem.isComplete();
            assert ((QuantityModuleItem) moduleItem).getCurrentQuantity().equals(0.0);
        }
    }

    @Nested
    @DisplayName("Tests for reset date calculation")
    class GetMostRecentReset {
        @Test
        @DisplayName("The most recent reset is calculated correctly for daily reset")
        public void testDailyReset() {
            Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);

            Instant testResetDate = now
                    .minus(2, ChronoUnit.DAYS)
                    .minus(8, ChronoUnit.HOURS);

            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.DAILY)
                    .withResetDate(testResetDate)
                    .build();

            assert moduleItem.getMostRecentReset().compareTo(now.minus(8, ChronoUnit.HOURS)) == 0;
        }

        @Test
        @DisplayName("The most recent reset is calculated correctly for weekly reset")
        public void testWeeklyReset() {
            Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);

            Instant testResetDate = now
                    .minus(2 * 7, ChronoUnit.DAYS)
                    .minus(3, ChronoUnit.DAYS);

            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.WEEKLY)
                    .withResetDate(testResetDate)
                    .build();

            assert moduleItem.getMostRecentReset().compareTo(now.minus(3, ChronoUnit.DAYS)) == 0;
        }

        @Test
        @DisplayName("The most recent reset is calculated correctly for monthly reset")
        public void testMonthlyReset() {
            Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);

            Instant testResetDate = DateUtils.addMonths(Date.from(now), -2)
                    .toInstant()
                    .minus(14, ChronoUnit.DAYS);


            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.MONTHLY)
                    .withResetDate(testResetDate)
                    .build();

            assert moduleItem.getMostRecentReset().compareTo(now.minus(14, ChronoUnit.DAYS)) == 0;
        }

        @Test
        @DisplayName("No recent reset date is returned when reset type is NONE")
        public void testNoneReset() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.NONE)
                    .build();

            assert moduleItem.getMostRecentReset() == null;
        }

        @Test
        @DisplayName("No recent reset date is returned when reset type is null")
        public void testNullReset() {
            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withRepeatType(null)
                    .build();

            assert moduleItem.getMostRecentReset() == null;
        }

        @Test
        @DisplayName("Returns the initial reset date when the reset date is recent")
        public void testInitialResetDate() {
            Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);

            Instant testResetDate = now
                    .minus(14, ChronoUnit.DAYS);

            ModuleItem<?> moduleItem = ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withRepeatType(ModuleItem.ModuleItemRepeatInterval.MONTHLY)
                    .withResetDate(testResetDate)
                    .build();

            assert moduleItem.getMostRecentReset().compareTo(now.minus(14, ChronoUnit.DAYS)) == 0;
        }
    }

    @Nested
    @DisplayName("Tests for updating item progress")
    class UpdateProgress {
        @Test
        @DisplayName("Sets item as complete if progress update completes base item")
        public void testBaseItemComplete() {
            Instant previousUpdate = Instant.now().minus(1, ChronoUnit.DAYS);

            BaseModuleItem moduleItem = (BaseModuleItem) ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withIsComplete(false)
                    .withLastUpdateDate(previousUpdate)
                    .build();

            assert moduleItem.getLastUpdatedTimestamp() == previousUpdate;

            moduleItem.updateProgress(true);

            assert moduleItem.isComplete();
            assert moduleItem.getLastUpdatedTimestamp().isAfter(previousUpdate);
        }

        @Test
        @DisplayName("Sets item as complete if progress update completes quantity item")
        public void testQuantityItemComplete() {
            Instant previousUpdate = Instant.now().minus(1, ChronoUnit.DAYS);

            QuantityModuleItem moduleItem = (QuantityModuleItem) ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                    .withQuantity(50.0, 100.0)
                    .withLastUpdateDate(previousUpdate)
                    .build();

            assert moduleItem.getLastUpdatedTimestamp() == previousUpdate;

            moduleItem.updateProgress(100.00);

            assert moduleItem.isComplete();
            assert moduleItem.getLastUpdatedTimestamp().isAfter(previousUpdate);
        }

        @Test
        @DisplayName("Sets item as incomplete if progress update does not complete base item")
        public void testBaseItemIncomplete() {
            Instant previousUpdate = Instant.now().minus(1, ChronoUnit.DAYS);

            BaseModuleItem moduleItem = (BaseModuleItem) ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.BASE)
                    .withIsComplete(false)
                    .withLastUpdateDate(previousUpdate)
                    .build();

            assert moduleItem.getLastUpdatedTimestamp() == previousUpdate;

            moduleItem.updateProgress(false);

            assert !moduleItem.isComplete();
            assert moduleItem.getLastUpdatedTimestamp().isAfter(previousUpdate);
        }

        @Test
        @DisplayName("Sets item as incomplete if progress update does not complete quantity item")
        public void testQuantityItemIncomplete() {
            Instant previousUpdate = Instant.now().minus(1, ChronoUnit.DAYS);

            QuantityModuleItem moduleItem = (QuantityModuleItem) ModuleItemBuilder.ofType(ModuleItem.ModuleItemType.QUANTITY)
                    .withQuantity(50.0, 100.0)
                    .withLastUpdateDate(previousUpdate)
                    .build();

            assert moduleItem.getLastUpdatedTimestamp() == previousUpdate;

            moduleItem.updateProgress(75.00);

            assert !moduleItem.isComplete();
            assert moduleItem.getLastUpdatedTimestamp().isAfter(previousUpdate);
        }
    }
}
