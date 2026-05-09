import { Book } from '../types'

interface BookCardProps {
  book: Book
  onClick: (book: Book) => void
}

export function BookCard({ book, onClick }: BookCardProps) {
  const stars = Math.round(book.averageRating)

  return (
    <div className="book-card" onClick={() => onClick(book)}>
      <div className="book-cover">
        {book.thumbnail ? (
          <img src={book.thumbnail} alt={book.title} loading="lazy" />
        ) : (
          <div className="book-cover-placeholder">
            <span>{book.title.charAt(0)}</span>
          </div>
        )}
        <div className="book-overlay">
          <span>Ver detalhes</span>
        </div>
      </div>
      <div className="book-info">
        <h3 className="book-title">{book.title}</h3>
        <p className="book-author">{book.authors.join(', ')}</p>
        {book.averageRating > 0 && (
          <div className="book-rating">
            {Array.from({ length: 5 }).map((_, i) => (
              <span key={i} className={i < stars ? 'star filled' : 'star'}>★</span>
            ))}
            <span className="rating-count">({book.ratingsCount})</span>
          </div>
        )}
      </div>
    </div>
  )
}
