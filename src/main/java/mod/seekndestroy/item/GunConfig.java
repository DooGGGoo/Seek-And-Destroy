package mod.seekndestroy.item;

import net.minecraft.sound.SoundEvent;

public class GunConfig
{
	public float damage;
	public float recoil;
	public float fireRate;

	public SoundEvent fireSound;

	public GunConfig() { }

	public GunConfig rangedDamage(float damage)
	{
		this.damage = damage;
		return this;
	}

	public GunConfig recoil(float recoil)
	{
		this.recoil = recoil;
		return this;
	}

	public GunConfig fireRate(float fireRate)
	{
		this.fireRate = fireRate;
		return this;
	}

	public GunConfig fireSound(SoundEvent fireSound)
	{
		this.fireSound = fireSound;
		return this;
	}
}
