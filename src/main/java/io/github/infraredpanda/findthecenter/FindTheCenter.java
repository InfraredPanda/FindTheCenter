package io.github.infraredpanda.findthecenter;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.spec.CommandSpec;

import com.google.inject.Inject;

import io.github.infraredpanda.findthecenter.commands.FindTheCenterExecutor;

@Plugin(id = "FindTheCenter", name = "FindTheCenter", version = "0.1", dependencies = "required-after:Inspector")
public class FindTheCenter
{
	public Game game;
	
	@Inject
	private Logger logger;

	public Logger getLogger()
	{
		return logger;
	}

	@Listener
	public void onServerPreInit(GamePreInitializationEvent event)
	{
		getLogger().info("FindTheCenter loading...");
	}

	@Listener
	public void onServerInit(GameInitializationEvent event)
	{
		game = event.getGame();
		
		CommandSpec findTheCenterCommandSpec = CommandSpec.builder()
			.description(Texts.of("FindTheCenter Command"))
			.permission("findthecenter.use")
			.executor(new FindTheCenterExecutor())
			.build();

		game.getCommandDispatcher().register(this, findTheCenterCommandSpec, "ftc", "findthecenter");
	}

	@Listener
	public void onServerPostInit(GamePostInitializationEvent event)
	{
		getLogger().info("FindTheCenter loaded!");
	}
}
