import discord
from discord.ext import commands
from discord import ui

# ⬇ paste your bot token below
TOKEN = ""
# ⬆ paste your bot token above

intents = discord.Intents.default()
intents.message_content = True

bot = commands.Bot(command_prefix='!', intents=intents)

swfd_fallen = False

@bot.command()
async def swfd(ctx):
    global swfd_fallen
    embed=discord.Embed(color=0xff9500)
    embed.add_field(name="", value="", inline=False)
    embed.set_image(url="https://i.imgur.com/pEPkZho.jpg")
    button = ui.Button(label="Make Stephen fall!")
    view = ui.View()
    view.add_item(button)
    await ctx.send(embed=embed, view=view)
    button.callback = on_interaction

@commands.Cog.listener()
async def on_interaction(interaction):
    view = ui.View()
    global swfd_fallen
    if swfd_fallen:
        button_edit = ui.Button(label="Make Stephen fall!")
        em=discord.Embed(color=0xff9500)
        em.add_field(name="", value="", inline=False)
        em.set_image(url="https://i.imgur.com/pEPkZho.jpg")
    else:
        button_edit = ui.Button(label="Make Stephen stand!")
        em=discord.Embed(color=0xff9500)
        em.add_field(name="Stephen Wolfram fell down!", value="", inline=False)
        em.set_image(url="https://i.imgur.com/gRqoRAW.jpg")
    swfd_fallen = not swfd_fallen
    view.add_item(button_edit)
    await interaction.response.edit_message(embed=em, view=view)
    button_edit.callback = on_interaction
    

bot.run(TOKEN)