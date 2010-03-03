/**
 * This file is part of aion-emu <aion-emu.com>.
 *
 *  aion-emu is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-emu is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aionemu.gameserver.configs;

import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.aionemu.commons.configuration.ConfigurableProcessor;
import com.aionemu.commons.configuration.Property;
import com.aionemu.commons.utils.PropertiesUtils;

/**
 * @author -Nemesiss-
 * @author SoulKeeper
 */
public class Config
{
	/**
	 * Logger for this class.
	 */
	protected static final Logger	log				= Logger.getLogger(Config.class);

	/**
	 * Game Server port
	 */
	@Property(key = "gameserver.network.client.port", defaultValue = "7777")
	public static int				GAME_PORT;

	/**
	 * Game Server bind ip
	 */
	@Property(key = "gameserver.network.client.host", defaultValue = "*")
	public static String			GAME_BIND_ADDRESS;

	/**
	 * Max allowed online players
	 */
	@Property(key = "gameserver.network.client.maxplayers", defaultValue = "100")
	public static int				MAX_ONLINE_PLAYERS;

	/**
	 * LoginServer address
	 */
	@Property(key = "gameserver.network.login.address", defaultValue = "localhost:9014")
	public static InetSocketAddress	LOGIN_ADDRESS;

	/**
	 * GameServer id that this GameServer will request at LoginServer.
	 */
	@Property(key = "gameserver.network.login.gsid", defaultValue = "0")
	public static int				GAMESERVER_ID;

	/**
	 * Password for this GameServer ID for authentication at LoginServer.
	 */
	@Property(key = "gameserver.network.login.password", defaultValue = "")
	public static String			LOGIN_PASSWORD;

	/**
	 * Number of Threads that will handle io read (>= 0)
	 */
	@Property(key = "gameserver.network.nio.threads.read", defaultValue = "0")
	public static int				NIO_READ_THREADS;

	/**
	 * Number of Threads that will handle io write (>= 0)
	 */
	@Property(key = "gameserver.network.nio.threads.write", defaultValue = "0")
	public static int				NIO_WRITE_THREADS;

	/**
	 * Number of Threads that will handle io write (>= 0)
	 */
	@Property(key = "gameserver.network.display.unknownpackets", defaultValue = "false")
	public static boolean				DISPLAY_UNKNOWNPACKETS;
	
	/**
	 * Interval for deadlock detector run schedule
	 */
	@Property(key = "gameserver.deadlock.interval", defaultValue = "300")
	public static int				DEADLOCK_DETECTOR_INTERVAL;
	
	/**
	 * Enable/disable deadlock detector
	 */
	@Property(key = "gameserver.deadlock.enable", defaultValue = "true")
	public static boolean			DEADLOCK_DETECTOR_ENABLED;

	/**
	 * Server name
	 */
	@Property(key = "gameserver.name", defaultValue = "aion private")
	public static String			SERVER_NAME;

	/**
	 * Character name pattern (checked when character is being created)
	 */
	@Property(key = "gameserver.character.name.pattern", defaultValue = "[a-zA-Z]{2,16}")
	public static Pattern			CHAR_NAME_PATTERN;

	/**
	 * Server Country Code
	 */
	@Property(key = "gameserver.country.code",defaultValue = "1")
	public static int				SERVER_COUNTRY_CODE;
	
	/*
	 * Server Mode
	 */
	@Property(key = "gameserver.mode",defaultValue = "1")
	public static int				SERVER_MODE;

	/*
	 * Factions speaking mode
	 */
	@Property(key = "gameserver.factions.speaking.mode",defaultValue = "0")
	public static int				FACTIONS_SPEAKING_MODE;
	
	/*
	 * Skill autolearn
	 */
	@Property(key = "gameserver.skill.autolearn",defaultValue = "true")
	public static boolean			SKILL_AUTOLEARN;
	
	/*
	 * Disable monsters aggressive behave
	 */
	@Property(key = "gameserver.disable.mob.aggro",defaultValue = "false")
	public static boolean			DISABLE_MOB_AGGRO;
	
	/*
	 * Enable 2nd class change simple mode
	 */
	@Property(key = "gameserver.enable.simple.2ndclass",defaultValue = "false")
	public static boolean			ENABLE_SIMPLE_2NDCLASS;

   	/*
	 * Unstuck delay
	 */
	@Property(key = "gameserver.unstuck.delay",defaultValue = "3600")
	public static int				UNSTUCK_DELAY;
	
	@Property(key = "gameserver.taskmanager.AllowGC", defaultValue = "false")
	public static boolean			ALLOW_GC;
	
	@Property(key = "gameserver.taskmanager.GCInterval", defaultValue = "3600000")
	public static int				GC_INTERVAL;

	@Property(key = "gameserver.ShutdownHookDelay", defaultValue = "60")
	public static int				SHUTDOWN_HOOK_DELAY;
	
	@Property(key = "gameserver.ShutdownAnnounceInterval", defaultValue = "1")
	public static int				SHUTDOWN_ANNOUNCE_INTERVAL;
	
	@Property(key = "gameserver.ShutdownHookMode", defaultValue = "1")
	public static int				SHUTDOWN_HOOK_MODE;

	/*
	 * Group
	 */
	@Property(key = "gameserver.playergroup.removetime", defaultValue = "600")
	public static int				GROUP_REMOVE_TIME;	
	
	@Property(key = "gameserver.playergroup.maxdistance", defaultValue = "100")
	public static int				GROUP_MAX_DISTANCE;	
	
	/**
	 * Initialize all configs in com.aionemu.gameserver.configs package
	 */
	public static void load()
	{
		try
		{
			Properties[] props = PropertiesUtils.loadAllFromDirectory("./config");

			ConfigurableProcessor.process(Config.class, props);
			ConfigurableProcessor.process(CacheConfig.class, props);
			ConfigurableProcessor.process(AdminConfig.class, props);
			ConfigurableProcessor.process(LegionConfig.class, props);
			ConfigurableProcessor.process(RateConfig.class, props);
		}
		catch(Exception e)
		{
			log.fatal("Can't load gameserver configuration", e);

			throw new Error("Can't load gameserver configuration", e);
		}

		IPConfig.load();
	}
}