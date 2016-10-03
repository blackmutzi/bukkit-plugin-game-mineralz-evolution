package com.plugins.mutzii.api;

import org.bukkit.entity.Player;
import com.plugins.mutzii.enums.Upgrades;

public interface UpgradeManager {

	public void upgrade(Upgrades upgrade,Player player);
}
