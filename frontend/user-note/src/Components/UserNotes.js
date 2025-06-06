import {useEffect } from 'react';
import {useNavigate} from 'react-router-dom';
import './UserNotes.css';
import {useNoteContext} from './NoteContext';

export const UserNotes = () => {
  const navigate = useNavigate();
  const {notes, getNotes} = useNoteContext();

  useEffect(() => {
    getNotes();
  },[]);

  const handleRowClick = (note) => {
    navigate(`/note-detail/${note.id}`, {state: {note}},{replace: true});
  };

  const handleLogout = () => {
    sessionStorage.removeItem("token");
    navigate("/",{replace: true});
  };


  const truncateNote = (note, maxLength) => {
    return note.length>maxLength?note.slice(0,maxLength)+'.........':note;
  };


  return (
    <>
    <div className="top-bar">
         <button className="logout-btn" onClick={handleLogout}><strong>LOGOUT</strong></button>
    </div>
    <div className="container">
      <div className="notes-box">
        <span className="notes-text">User Notes</span>
        <button className="add-btn" onClick={() => navigate('/add-note',{replace: true})}>
          <span className="plus">+</span> <strong>ADD</strong>
        </button>
      </div>
      <table className="notes-table">
        <thead>
          <tr>
            <th className="date-col">Date</th>
            <th>Preview</th>
          </tr>
        </thead>
        <tbody>
          {notes.map((item, index) => (
            <tr key={index} onClick={() => handleRowClick(item)}>
              <td style={{textAlign: 'center'}} className="date"><strong>{item.created.slice(0,10)}</strong></td>
              <td>{truncateNote(item.note,40)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
   </>
  );
};
