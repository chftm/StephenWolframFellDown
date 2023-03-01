extends Sprite

var standing = true

func _on_Button_pressed():
	if standing:
		rotation_degrees = -90
	else:
		rotation_degrees = 0
	standing = not standing
