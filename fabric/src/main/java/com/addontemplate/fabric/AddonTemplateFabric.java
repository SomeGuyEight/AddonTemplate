package com.addontemplate.fabric;

import com.addontemplate.common.AddonTemplate;
import com.addontemplate.common.TemplateModImplementation;
import com.addontemplate.common.commands.TemplateCommands;
import com.cobblemontournament.common.config.ConfigProvider;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

public class AddonTemplateFabric implements ModInitializer, TemplateModImplementation
{
    @Override
    public void onInitialize() {
        AddonTemplate.initialize( this );
    }
    
    @Override
    public void initializeConfig() {
        ConfigProvider.registerConfigs( FabricLoader.getInstance().getConfigDir() );
    }
    
    @Override
    public void registerEvents() {
        AddonTemplate.registerEvents();
    }
    
    @Override
    public void registerCommands() {
        CommandRegistrationCallback.EVENT.register( TemplateCommands::register );
    }
    
}
