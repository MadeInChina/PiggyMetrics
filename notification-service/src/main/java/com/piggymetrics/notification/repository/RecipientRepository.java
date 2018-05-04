package com.piggymetrics.notification.repository;

import com.piggymetrics.notification.domain.Recipient;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepository extends MongoRepository<Recipient, String> {

  Recipient findByAccountName(String name);

  @Query(
      "{ $and: [ {'scheduledNotifications.BACKUP.active': true }, { $where: 'this.scheduledNotifications.BACKUP.lastNotified < "
          + "new Date(new Date().setDate(new Date().getDate() - this.scheduledNotifications.BACKUP.frequency ))' }] }")
  List<Recipient> findReadyForBackup();

  @Query(
      "{ $and: [ {'scheduledNotifications.REMIND.active': true }, { $where: 'this.scheduledNotifications.REMIND.lastNotified < "
          + "new Date(new Date().setDate(new Date().getDate() - this.scheduledNotifications.REMIND.frequency ))' }] }")
  List<Recipient> findReadyForRemind();
}
