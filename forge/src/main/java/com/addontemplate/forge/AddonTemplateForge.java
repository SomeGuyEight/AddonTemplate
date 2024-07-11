package com.addontemplate.forge;

import com.addontemplate.common.AddonTemplate;
import com.addontemplate.common.TemplateModImplementation;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("unused")
@Mod(AddonTemplate.MOD_ID)
public class AddonTemplateForge implements TemplateModImplementation
{
    public AddonTemplateForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(AddonTemplate.MOD_ID,FMLJavaModLoadingContext.get().getModEventBus());
        AddonTemplate.initialize( this );
    }

    @Override
    public void initializeConfig()
    {
    
    }
    
    @Override
    public void registerEvents()
    {
    
    }
    
    @Override
    public void registerCommands()
    {
    
    }

}