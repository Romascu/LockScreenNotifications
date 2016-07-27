/*
 * Copyright (C) 2014 AChep@xda <artemchep@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package com.acrr.acdisplay.ui.activities.settings;

import android.support.annotation.NonNull;
import android.support.annotation.XmlRes;

import com.acrr.acdisplay.R;
import com.acrr.acdisplay.services.activemode.ActiveModeService;
import com.acrr.acdisplay.ui.fragments.settings.ActiveModeSettings;
import com.acrr.acdisplay.ui.fragments.settings.InterfaceSettings;
import com.acrr.acdisplay.ui.fragments.settings.KeyguardSettings;
import com.acrr.acdisplay.ui.fragments.settings.MoreSettings;
import com.acrr.acdisplay.ui.fragments.settings.NotificationSettings;
import com.acrr.base.dashboard.DashboardTile;
import com.acrr.base.ui.activities.SettingsActivity;

import static com.acrr.base.Build.DEBUG;

/**
 * Created by Artem Chepurnoy on 02.01.2015.
 */
public class Settings2 extends SettingsActivity {

    private static final String[] ENTRY_FRAGMENTS = {
            KeyguardSettings.class.getName(),
            ActiveModeSettings.class.getName(),
            NotificationSettings.class.getName(),
            InterfaceSettings.class.getName(),
            MoreSettings.class.getName()
    };

    @Override
    protected boolean isValidFragment(@NonNull String fragmentName) {
        // Almost all fragments are wrapped in this,
        // except for a few that have their own activities.
        for (String fragment : ENTRY_FRAGMENTS)
            if (fragment.equals(fragmentName))
                return true;
        return false;
    }

    @XmlRes
    @Override
    public int getDashboardResource() {
        return R.xml.settings_dashboard;
    }

    @Override
    protected boolean isTileSupported(@NonNull DashboardTile tile) {
        switch ((int) tile.id) {
            case R.id.dev_settings:
                return DEBUG;
            case R.id.active_settings:
                return ActiveModeService.isSupported(this);
        }
        return true;
    }
}
