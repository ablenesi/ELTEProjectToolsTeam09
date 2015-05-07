#include "Entity.h"

void Entity::setId(int _id) {
	id = _id;
}

int Entity::getId(){
	return id;
}

bool Entity::operator==(const Entity& entity) {
	return entity.id == id;
}


