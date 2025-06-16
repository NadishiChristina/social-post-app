import React, { useEffect, useState } from 'react';
import { getUsers } from '../api';

const ContactsList = () => {
  const [users, setUsers] = useState([]); // State to hold fetched users

  useEffect(() => {
    getUsers().then((res) => setUsers(res.data));
  }, []);

  return (
    <div className="p-6 max-w-2xl mx-auto">

      {/* Section Heading */}
      <h5 className="text-2xl font-bold mb-6 text-white">My Contacts</h5>

      {/* Contact List */}
      <div className="space-y-4">

        {/* Loop through the users array and display each contact */}
        {users.map((user) => (
          <div
            key={user.id}
            className="contact-item contact-gradient"
          >

            {/* Avatar */}
            <div className="flex-shrink-0">
              <img
                src={`https://i.pravatar.cc/64?u=${user.email}`}
                alt={`${user.name}'s avatar`}
                className="contact-avatar"
              />
            </div>

            {/* User Info */}
            <div className="contact-info flex-1 ml-4">
              <div className="contact-name">
                {user.name}
              </div>
              <div className="contact-email">
                {user.email}
              </div>
              <div className="contact-phone">
                {user.phone}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ContactsList;