# EasyBan

EasyBan is a Plugin to Simplify ban's on you Minecraft Server

## Installation

Download the plugin from [Github](https://github.com/Bmxertv/EasyBan). Now move
the `EasyBan-RELEASE.jar` into the plugin's folder of the server and state the server afterwards. As soon as the server is
started, a new folder named `EasyBan` will be created in the plugin's folder. In this folder you will find a `config.yml` now
you can start to set your ban reasons, and you can also set the format of all dates
here [DateTime Patterns](https://www.baeldung.com/java-datetimeformatter#dateFormatter).

## Configuration
```yml
dateTimeFormate: "HH:mm:ss dd.MM.yyyy" # The Pattern for all Dates
reasons:
  # To create a new Reason set a id and all Properties for this Object 
  1:
    name: Hacking
    message:
      # You can use the %reason%, %from% and %until% 
      # placeholders to dynamically deliver your message.
      # Also, color codes can be used
      - "&7&m-----------"
      - "&r&7Du wurdest gebannt wegen &cHacking"
      - "&r&7von %from%"
      - "&r&7bis %until%"
    time:
      # Set the length of a ban in seconds, minutes, hours,
      # days, weeks, months and years
      seconds: 0
      minutes: 0
      hours: 0
      days: 0
      weeks: 1
      months: 0
      years: 0
  2:
    name: Lifetime
    # In addition, the parameter customReason can be specified.
    # Use the placeholder %reason% in the message to display the custom reason.
    customReason: true
    message:
      - "&7&m-----------"
      - "&r&7Du wurdest gebannt wegen %reason%"
      - "&r&7von %from%"
      - "&r&7bis %until%"
    time:
      seconds: 0
      minutes: 0
      hours: 0
      days: 0
      weeks: 0
      months: 0
      years: 999
```

## TODO
- [X] Ban command
- [X] Unban command
- [X] Disallow connect if player is banned
- [X] Save Banned players in config
- [X] Automatic unban player when the ban time is up
- [ ] Inform the owner when a new version of this plugin is available

## Welcome to GitHub Pages

You can use the [editor on GitHub](https://github.com/Bmxertv/EasyBan/edit/gh-pages/index.md) to maintain and preview the content for your website in Markdown files.

Whenever you commit to this repository, GitHub Pages will run [Jekyll](https://jekyllrb.com/) to rebuild the pages in your site, from the content in your Markdown files.

### Markdown

Markdown is a lightweight and easy-to-use syntax for styling your writing. It includes conventions for

```markdown
Syntax highlighted code block

# Header 1
## Header 2
### Header 3

- Bulleted
- List

1. Numbered
2. List

**Bold** and _Italic_ and `Code` text

[Link](url) and ![Image](src)
```

For more details see [Basic writing and formatting syntax](https://docs.github.com/en/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax).

### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/Bmxertv/EasyBan/settings/pages). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://docs.github.com/categories/github-pages-basics/) or [contact support](https://support.github.com/contact) and we’ll help you sort it out.
