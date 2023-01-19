import pygame

W = 900
H = 600
fps = 30

pygame.init()
screen = pygame.display.set_mode((W,H))
pygame.display.set_caption("My Game")
clock = pygame.time.Clock()

class IMG(pygame.sprite.Sprite):
    def __init__(self, x, y, file):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load(file).convert_alpha()
        self.rect = self.image.get_rect(center = (x,y))
    def update(self):
        self.image = pygame.transform.rotate(self.image, 90)

class BT(pygame.sprite.Sprite):
    def __init__(self, x, y, file):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load(file).convert_alpha()
        self.rect = self.image.get_rect(center = (x,y))
    def is_clicked(self):
        return pygame.mouse.get_pressed()[0] and self.rect.collidepoint(pygame.mouse.get_pos())


stephen = IMG(W//2, H//2.5, '178185_320.jpg')
button = BT(W//2,H//1.2, 'photo_2022-12-30_02-11-48.jpg')

f1 = pygame.font.Font(None, 36)
text1 = f1.render('Покрути Вольфрама!', True,(180, 0, 0))

running = True
screen.fill((200,200,200))
pygame.display.flip()
while running:
    clock.tick(fps)
    screen.fill((200,200,200))
    screen.blit(stephen.image, stephen.rect)
    screen.blit(button.image, button.rect)
    screen.blit(text1, (320, 40))
    if pygame.mouse.get_pressed()[0] and button.rect.collidepoint(pygame.mouse.get_pos()):
        stephen.update()
    pygame.display.flip()
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = false
pygame.quit()