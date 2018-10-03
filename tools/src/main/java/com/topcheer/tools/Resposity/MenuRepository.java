package com.topcheer.tools.Resposity;

import com.topcheer.tools.entity.MenuUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuUrl,Integer> {
    @Query("from MenuUrl where parentnoteid<=0 and roles_id<=?1")
    List<MenuUrl> findAllByRolesId1(Integer roleId);


    @Query("from MenuUrl where parentnoteid>0 and roles_id<=?1")
    List<MenuUrl> findAllByRolesId2(Integer roleId);
}
