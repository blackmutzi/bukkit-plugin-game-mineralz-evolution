package com.plugins.mutzii.plugin;

import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.permission.Permission;

public abstract class VaultPermission {
	
	private Plugin m_plugin;
	private Permission m_permission;
	
	
	public VaultPermission( Plugin plugin_instance )
	{
		 m_plugin            = plugin_instance;
		 Plugin vault_plugin = m_plugin.getServer().getPluginManager().getPlugin("Vault");
		 
		 if( vault_plugin != null )
		 {
			  m_plugin.getLogger().log(Level.INFO, "Vault plugin detected");
			  RegisteredServiceProvider<Permission> provider = m_plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
			  
			  if( provider != null )
			  {
				  m_permission = provider.getProvider();
			  }
			  
		 }	 
	}
	
	public boolean hasPermission( Player player , String permission )
	{	
		return m_permission.has(player, permission);
	}
	

}
