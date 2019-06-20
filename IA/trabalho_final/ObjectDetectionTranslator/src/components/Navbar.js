import React from 'react';
import { Navbar } from 'react-bootstrap';

const MyNavbar = () => {
  return (
    <div>
      <Navbar bg="dark" >
        <Navbar.Brand style={{ color: 'white' }} >Object Detection Translator</Navbar.Brand>
      </Navbar>
      <br />
    </div>
  );
}

export default MyNavbar;