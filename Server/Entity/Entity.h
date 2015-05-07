#ifndef ENTITY_H
#define ENTITY_H

class Entity {
protected:
	int id;
public:
	void setId(int _id);
	int getId();
	bool operator==(const Entity& entity);
};

#endif
