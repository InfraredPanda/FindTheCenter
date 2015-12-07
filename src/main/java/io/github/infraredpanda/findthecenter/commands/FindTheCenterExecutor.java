package io.github.infraredpanda.findthecenter.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import io.github.hsyyid.inspector.Inspector;
import io.github.hsyyid.inspector.utilities.Region;

public class FindTheCenterExecutor implements CommandExecutor
{
	public CommandResult execute(CommandSource src, CommandContext ctx) throws CommandException
	{
		if (src instanceof Player)
		{
			Player player = (Player) src;

			Region playerRegion = null;

			for (Region region : Inspector.regions)
			{
				if (region.getOwner().equals(player.getUniqueId()))
				{
					playerRegion = region;
					break;
				}
			}

			if (playerRegion != null)
			{
				double x = (playerRegion.getPointA().getX() + playerRegion.getPointB().getX())/2;
				double y = (playerRegion.getPointA().getY() + playerRegion.getPointB().getY())/2;
				double z = (playerRegion.getPointA().getZ() + playerRegion.getPointB().getZ())/2;
				
				player.sendMessage(Texts.of(TextColors.GREEN, "Teleporting to the Center..."));
				player.setLocation(new Location<World>(player.getWorld(), x, y, z));
			}
			else
			{
				player.sendMessage(Texts.of(TextColors.RED, "You have no region."));
			}
		}
		else if (src instanceof ConsoleSource)
		{
			src.sendMessage(Texts.of(TextColors.DARK_RED, "Error! ", TextColors.RED, "Must be an in-game player to use /ftc!"));
		}
		else if (src instanceof CommandBlockSource)
		{
			src.sendMessage(Texts.of(TextColors.DARK_RED, "Error! ", TextColors.RED, "Must be an in-game player to use /ftc!"));
		}

		return CommandResult.success();
	}
}
