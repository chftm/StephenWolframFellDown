extends Button

var standing = true

func _pressed():
	if standing:
		text = "ПОДНЯТЬ"
	else:
		text = "УРОНИТЬ"
	standing = not standing
