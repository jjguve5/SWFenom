package pt.feromonas.skywars;

import org.bukkit.Bukkit;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.World;
import java.util.Random;
import org.bukkit.Location;

public final class Cuboid
{
    private final Location highPoints;
    private final Location lowPoints;
    
    public Cuboid(final Location startLoc, final Location endLoc) {
        final int lowx = Math.min(startLoc.getBlockX(), endLoc.getBlockX());
        final int lowy = Math.min(startLoc.getBlockY(), endLoc.getBlockY());
        final int lowz = Math.min(startLoc.getBlockZ(), endLoc.getBlockZ());
        final int highx = Math.max(startLoc.getBlockX(), endLoc.getBlockX());
        final int highy = Math.max(startLoc.getBlockY(), endLoc.getBlockY());
        final int highz = Math.max(startLoc.getBlockZ(), endLoc.getBlockZ());
        this.highPoints = new Location(startLoc.getWorld(), (double)highx, (double)highy, (double)highz);
        this.lowPoints = new Location(startLoc.getWorld(), (double)lowx, (double)lowy, (double)lowz);
    }
    
    public boolean isAreaWithinArea(final Cuboid area) {
        return this.containsLoc(area.highPoints) && this.containsLoc(area.lowPoints);
    }
    
    public boolean containsLoc(final Location loc) {
        return loc != null && loc.getWorld().equals(this.highPoints.getWorld()) && (this.lowPoints.getBlockX() <= loc.getBlockX() && this.highPoints.getBlockX() >= loc.getBlockX() && this.lowPoints.getBlockZ() <= loc.getBlockZ() && this.highPoints.getBlockZ() >= loc.getBlockZ() && this.lowPoints.getBlockY() <= loc.getBlockY() && this.highPoints.getBlockY() >= loc.getBlockY());
    }
    
    public boolean containsLocWithoutY(final Location loc) {
        return loc != null && loc.getWorld().equals(this.highPoints.getWorld()) && (this.lowPoints.getBlockX() <= loc.getBlockX() && this.highPoints.getBlockX() >= loc.getBlockX() && this.lowPoints.getBlockZ() <= loc.getBlockZ() && this.highPoints.getBlockZ() >= loc.getBlockZ());
    }
    
    public long getSize() {
        return Math.abs(this.getXSize() * this.getYSize() * this.getZSize());
    }
    
    public Location getRandomLocation() {
        final World world = this.getWorld();
        final Random randomGenerator = new Random();
        Location result = new Location(world, (double)this.highPoints.getBlockX(), (double)this.highPoints.getBlockY(), this.highPoints.getZ());
        if (this.getSize() > 1L) {
            final double randomX = this.lowPoints.getBlockX() + randomGenerator.nextInt(this.getXSize());
            final double randomY = this.lowPoints.getBlockY() + randomGenerator.nextInt(this.getYSize());
            final double randomZ = this.lowPoints.getBlockZ() + randomGenerator.nextInt(this.getZSize());
            result = new Location(world, randomX, randomY, randomZ);
        }
        return result;
    }
    
    public Location getRandomLocationForMobs() {
        final Location temp = this.getRandomLocation();
        return new Location(temp.getWorld(), temp.getBlockX() + 0.5, temp.getBlockY() + 0.5, temp.getBlockZ() + 0.5);
    }
    
    public int getXSize() {
        return this.highPoints.getBlockX() - this.lowPoints.getBlockX() + 1;
    }
    
    public int getYSize() {
        return this.highPoints.getBlockY() - this.lowPoints.getBlockY() + 1;
    }
    
    public int getZSize() {
        return this.highPoints.getBlockZ() - this.lowPoints.getBlockZ() + 1;
    }
    
    public Location getHighLoc() {
        return this.highPoints;
    }
    
    public Location getLowLoc() {
        return this.lowPoints;
    }
    
    public World getWorld() {
        return this.highPoints.getWorld();
    }
   
    
    @Override
    public String toString() {
        return "(" + this.lowPoints.getBlockX() + ", " + this.lowPoints.getBlockY() + ", " + this.lowPoints.getBlockZ() + ") to (" + this.highPoints.getBlockX() + ", " + this.highPoints.getBlockY() + ", " + this.highPoints.getBlockZ() + ")";
    }
    
    public String toRaw() {
        return this.getWorld().getName() + "," + this.lowPoints.getBlockX() + "," + this.lowPoints.getBlockY() + "," + this.lowPoints.getBlockZ() + "," + this.highPoints.getBlockX() + "," + this.highPoints.getBlockY() + "," + this.highPoints.getBlockZ();
    }
}
